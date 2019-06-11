package ch.heigvd.mcr.bridgehack.character;

import ch.heigvd.mcr.bridgehack.Item.*;
import ch.heigvd.mcr.bridgehack.Item.potion.*;
import ch.heigvd.mcr.bridgehack.Item.weapon.BareHanded;
import ch.heigvd.mcr.bridgehack.Item.weapon.Weapon;
import ch.heigvd.mcr.bridgehack.character.roles.Role;
import ch.heigvd.mcr.bridgehack.game.Map;
import ch.heigvd.mcr.bridgehack.character.races.Race;
import ch.heigvd.mcr.bridgehack.utils.IntVector;

import org.newdawn.slick.*;

import java.util.LinkedList;

import lombok.Getter;
import lombok.Setter;

/**
 * Class representing a character
 */
public abstract class Character {
    @Getter
    @Setter
    private String name;
    @Getter
    protected int x;
    @Getter
    protected int y;
    protected State playerState;
    protected boolean moving = false;
    @Getter
    protected int direction = 0;
    protected Map map;
    @Getter
    private LinkedList<Item> inventory;
    @Setter
    private Weapon weapon;
    private Race race;

    /**
     * Constructor for the character.
     *
     * @param race the initial race of the character
     * @param map a reference to the map for collision detection
     */
    public Character(Race race, Map map) {
        this.race = race;
        this.map = map;
        setRandomPos();

        playerState = new State();
        if (race != null) {
            playerState.addModifier(race.getStatModifier());
        }

        inventory = new LinkedList<>();
        inventory.add(new TransformPotion());
        inventory.add(new TransformPotion());
        inventory.add(new TransformPotion());
        weapon = new BareHanded();
    }

    /**
     * Helper function to generate random coordinates for the player
     */
    private void setRandomPos() {
        IntVector pos = map.getRandomPos();
        x = pos.getX();
        y = pos.getY();
    }

    /**
     * Stops the movements from the player
     */
    public void stop() {
        moving = false;
    }

    /**
     * Displays the player in the graphics given
     *
     * @param g the Graphics in which it will be rendered
     */
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(x, y + 8, 16, 8);

        race.render(g, moving, x, y);
    }

    /**
     * Attack in a certain direction
     *
     * @param direction the direction in which the player attacks
     */
    public void attack(int direction) {
        Enemy enemyToAttack = null;

        boolean createPortal = false;

        if (weapon instanceof Map.GoldenSword) {
            createPortal = true;
        }

        for (int i = 1; i <= weapon.getRange(); ++i) {
            switch (direction) {
                case 0: // up
                    if (createPortal) {
                        map.createPortal(x, y - 16);
                    }
                    if (map.isCollision(x, y - (i * 16))) {
                        return;
                    }
                    enemyToAttack = checkForEnemy(new IntVector(x, y - (i * 16)));
                    break;
                case 1: // left
                    if (createPortal) {
                        map.createPortal(x - 16, y);
                    }
                    if (map.isCollision(x - (i * 16), y)) {
                        return;
                    }
                    enemyToAttack = checkForEnemy(new IntVector(x - (i * 16), y));
                    break;
                case 2: // down
                    if (createPortal) {
                        map.createPortal(x, y + 16);
                    }
                    if (map.isCollision(x, y + (i * 16))) {
                        return;
                    }
                    enemyToAttack = checkForEnemy(new IntVector(x, y + (i * 16)));
                    break;
                case 3: // right
                    if (createPortal) {
                        map.createPortal(x + 16, y);
                    }
                    if (map.isCollision(x + (i * 16), y)) {
                        return;
                    }
                    enemyToAttack = checkForEnemy(new IntVector(x + (i * 16), y));
                    break;
            }
            if (enemyToAttack != null) {
                enemyToAttack.receiveDamage(weapon.attack(playerState));
                break;
            }
        }
    }

    /**
     * Check if there is an enemy in a given position
     *
     * @param pos the position to test
     * @return
     */
    private Enemy checkForEnemy(IntVector pos) {
        for (Enemy enemy : map.getEnemies()) {
            if (enemy.getX() == pos.getX() && enemy.getY() == pos.getY()) {
                return enemy;
            }
        }
        return null;
    }

    /**
     * Returns a resume of the player status
     *
     * @return a resume of the player status
     */
    public String getStatus() {
        return name + " the " + race + " " + race.getRole() + " " + playerState.toString();
    }

    /**
     * Changes the map of the player, necessary when a ladder is taken
     *
     * @param map the new map
     */
    public void setMap(Map map) {
        this.map = map;
        setRandomPos();
    }

    /**
     * Setter for the race, takes statModifiers into account
     * @param race the new race
     */
    public void setRace(Race race) {
        if (this.race != null) {
            playerState.removeModifier(this.race.getStatModifier());
        }
        this.race = race;
        playerState.addModifier(this.race.getStatModifier());
    }

    /**
     * Changes the role of the character with statModifiers into account
     * @param role the new role
     */
    public void changeRole(Role role) {
        playerState.removeModifier(this.race.getStatModifier());
        race.setRole(role);
        playerState.addModifier(race.getStatModifier());
    }

    /**
     * Renders various text informations about the character, such as
     * inventory and stats
     * @param ttf the context to draw in
     */
    public void renderText(TrueTypeFont ttf) {
        // Display the inventory
        for (int i = 0; i < inventory.size(); ++i) {
            ttf.drawString(1000, 50 + 20 * i, i + " - " + inventory.get(i));
        }

        ttf.drawString(0, 660, getStatus());
    }

    /**
     * Tries to drink a potion at a given inventory slot. Return 0 if successful
     * and -1 otherwise
     * @param index the slot in the inventory to drink to potion
     * @return whether the character could successfully drink the potion
     * @throws SlickException
     */
    public int drink(int index) throws SlickException {
        if (index < inventory.size()) {
            if (inventory.get(index) instanceof Weapon) {
                playerState.setHealth(playerState.getHealth() / 2);
                inventory.remove(index);
                return -1;
            }
            ((Potion) inventory.get(index)).drink(this);
            inventory.remove(index);
        }
        return 0;
    }

    /**
     * Equip a weapon
     *
     * @param index the index in the inventory
     */
    public void equip(int index) {
        if (index < inventory.size()) {
            if (inventory.get(index) instanceof Weapon) {
                Weapon tempWeapon = weapon;
                weapon = (Weapon) inventory.get(index);
                inventory.remove(weapon);
                if (!(tempWeapon instanceof BareHanded)) {
                    inventory.add(tempWeapon);
                }
            }
        }
    }

    /**
     * Remove an item from the inventory
     *
     * @param index The index in the inventory
     */
    public void deleteItem(int index) {
        if (index < inventory.size()) {
            inventory.remove(index);
        }
    }

    /**
     * Restores the player's health back to full
     */
    public void restoreHealth() {
        playerState.restoreHealth();
    }

    /**
     * Add an item in the inventory
     *
     * @param item the item to add
     */
    public void giveItem(Item item) {
        inventory.add(item);
    }

    /**
     * Refreshes the position of the character if it's moving.
     * Should also check for collisions, combat and interact
     * with items and environment
     *
     * @param delta the time elapsed since the last tick
     */
    public abstract void update(int delta);

    /**
     * Lowers the health of a character
     *
     * @param damage the damage to inflict
     */
    public void receiveDamage(int damage) {
        playerState.setHealth(playerState.getHealth() - damage);
        if (playerState.getHealth() <= 0) {
            map.killCharacter(this);
        }
    }
}
