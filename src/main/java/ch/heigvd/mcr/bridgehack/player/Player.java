package ch.heigvd.mcr.bridgehack.player;

import ch.heigvd.mcr.bridgehack.Item.*;
import ch.heigvd.mcr.bridgehack.Item.potion.*;
import ch.heigvd.mcr.bridgehack.game.Map;
import ch.heigvd.mcr.bridgehack.player.roles.Role;
import ch.heigvd.mcr.bridgehack.utils.IntVector;

import org.newdawn.slick.*;
import java.util.LinkedList;
import lombok.Getter;
import lombok.Setter;

/**
 * Class representing a player
 */
public class Player {
    // Base path to image resources
    static final private String IMG_BASE_PATH = "/src/main/resources/img/";

    @Getter
    @Setter
    private String name;
    private State playerState;
    @Getter
    private int x;
    @Getter
    private int y;
    private boolean moving = false;
    private int direction = 0;
    private Map map;
    private LinkedList<Item> inventory;
    private Animation idleAnimation = new Animation();
    private Animation runAnimation = new Animation();

    private Role role;

    /**
     * Constructor for the players character.
     *
     * @param role the initial role of the character
     * @param map a reference to the map for collision detection
     * @throws SlickException if a problem occurred building the animations
     */
    public Player(Role role, Map map) throws SlickException {
        this.role = role;
        this.map = map;
        setRandomPos();

        String imageBasePath = IMG_BASE_PATH + role.getBaseImageNameImpl();
        playerState = new State();
        inventory = new LinkedList<>();
        inventory.add(new TransformPotion());
        inventory.add(new ManaPotion());
        playerState = new State();

        for (int i = 0; i < 4; ++i) {
            idleAnimation.addFrame(new Image(imageBasePath + "_idle_anim_f" + i + ".png"), 100);
            runAnimation.addFrame(new Image(imageBasePath + "_run_anim_f" + i + ".png"), 100);
        }
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
     * Sets the player in motion to a certain direction
     *
     * @param direction the direction in which the player has to go
     */
    public void move(int direction) {
        this.direction = direction;
        moving = true;
    }

    /**
     * Stops the movements from the player
     */
    public void stop() {
        moving = false;
    }

    /**
     * Refreshes the position of the player if it's moving.
     * Should also check for collisions, combat and interact
     * with items and environment
     *
     * @param delta the time elapsed since the last tick
     */
    public void update(int delta) {
        if (moving) {
            int futureX = x, futureY = y;
            boolean collision = false;

            switch (direction) {
                case 0:
                    futureY -= 1;
                    collision = map.isCollision(x, y - 1);
                    break;
                case 1:
                    futureX -= 1;
                    collision = map.isCollision(x - 1, y);
                    break;
                case 2:
                    futureY += 1;
                    collision = map.isCollision(x, y + 16);
                    break;
                case 3:
                    futureX += 1;
                    collision = map.isCollision(x + 16, y);
                    break;
            }

            if (collision) {
                moving = false;
                return;
            }

            x = futureX;
            y = futureY;

            if ((x % 16) == 8 && (y % 16) == 8) {
                moving = false;
            }
        }
    }

    /**
     * Displays the player in the graphics given
     *
     * @param g the Graphics in which it will be rendered
     */
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(x, y + 8, 16, 8);

        if (moving) {
            g.drawAnimation(runAnimation, x, y - 16);
        } else {
            g.drawAnimation(idleAnimation, x, y - 16);
        }
    }

    /**
     * Attack in a certain direction
     *
     * @param direction the direction in which the player attacks
     */
    public void attack(int direction) {
        //TO DO
        System.out.println("I'm attacking on direction " + direction);
    }

    /**
     * Returns a resume of the player status
     *
     * @return a resume of the player status
     */
    public String getStatus() {
        return name + " the " + role + " " + playerState.toString();
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
     * Restores the player's mana back to full
     */
    public void restoreMana() {
        playerState.restoreMana();
    }

    public void changeRole(Role role) {
        this.role = role;
    }

    public void renderText(TrueTypeFont ttf) {
        // Display the inventory
        for(int i = 0; i < inventory.size(); ++i) {
            ttf.drawString(1000, 50 + 20 * i, i + " - " + inventory.get(i));
        }

        ttf.drawString(0, 660, getStatus());
    }

    public void drink(int index) {
        //TO DO Check if the item at index i is indeed a potion
        ((Potion) inventory.get(index)).drink(this);
        inventory.remove(index);
    }

    /**
     * Restores the player's health back to full
     */
    public void restoreHealth() {
        playerState.restoreHealth();
    }
}
