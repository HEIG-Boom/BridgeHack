package ch.heigvd.mcr.bridgehack.game;

import ch.heigvd.mcr.bridgehack.Item.weapon.Weapon;
import ch.heigvd.mcr.bridgehack.character.Character;
import ch.heigvd.mcr.bridgehack.character.Enemy;
import ch.heigvd.mcr.bridgehack.character.Player;
import ch.heigvd.mcr.bridgehack.character.races.Human;
import ch.heigvd.mcr.bridgehack.character.races.Undead;
import ch.heigvd.mcr.bridgehack.character.roles.BadGuy;
import ch.heigvd.mcr.bridgehack.character.roles.Wizard;
import ch.heigvd.mcr.bridgehack.utils.IntVector;
import lombok.Getter;
import lombok.Setter;
import ch.heigvd.mcr.bridgehack.Item.Item;
import ch.heigvd.mcr.bridgehack.Item.potion.HealthPotion;
import ch.heigvd.mcr.bridgehack.Item.potion.TransformPotion;
import ch.heigvd.mcr.bridgehack.Item.weapon.Bow;
import ch.heigvd.mcr.bridgehack.Item.weapon.Staff;
import ch.heigvd.mcr.bridgehack.Item.weapon.Sword;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.LinkedList;
import java.util.Random;

/**
 * This class represents the map of the game.
 */
public class Map {
    private static final int NUMBER_OF_ENEMIES = 5;
    private TiledMap map;
    private Exit exit;
    private Random rand;
    @Getter
    private int index;
    @Getter
    private GoldenSword goldenSword;
    @Setter
    @Getter
    private Player player;
    private Portal portal;
    @Getter
    private LinkedList<Enemy> enemies = new LinkedList<>(); // list of enemies on the map
    private LinkedList<Chest> chests = new LinkedList<>();

    /**
     * General constructor for a basic map
     *
     * @param index  the floor corresponding to this map
     * @param isLast if this map is the last one
     */
    public Map(int index, boolean isLast) {
        this.index = index;
        rand = new Random();
        try {
            this.map = new TiledMap("/src/main/resources/maps/map" + index + ".tmx");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        // Generate enemies on the map
        generateEnemies();

        if (!isLast) {
            exit = new Exit();
        } else {
            goldenSword = new GoldenSword();
        }
        for (int i = 0; i < 5; ++i) {
            chests.add(new Chest());
        }
    }

    /**
     * Returns whether the tile of given position has collision.
     * (tldr. if the collision layer has a tile at those coordinates)
     *
     * @param x the x coordinate to verify
     * @param y the y coordinate to verify
     * @return whether the tile of given position has collision.
     */
    public boolean isCollision(int x, int y) {
        Image tile = map.getTileImage(x / map.getTileWidth(), y / map.getTileHeight(), 0);
        return tile != null;
    }

    /**
     * Renders the map
     */
    public void renderObjects(Graphics g) {
        if (exit != null) {
            exit.render(g);
        }
        if (goldenSword != null) {
            goldenSword.render(g);
        }
        if (portal != null) {
            portal.render(g);
        }
        for (Chest chest : chests) {
            chest.render(g);
        }
    }

    public void createPortal(int x, int y) {
        portal = new Portal(x,y);
    }

    /**
     * Renders only a layer of the map
     *
     * @param layer the layer to be rendered.
     */
    public void render(int layer) {
        map.render(0, 0, layer);
    }

    /**
     * Returns a pair of two random coordinates that are on a unoccupied floor tile
     *
     * @return a pair of two random coordinates that are on a unoccupied floor tile
     */
    public IntVector getRandomPos() {
        int x, y;
        do {
            x = rand.nextInt(map.getWidth());
            y = rand.nextInt(map.getHeight());
        } while (map.getTileImage(x, y, 1) == null);
        x *= map.getTileWidth();
        y *= map.getTileHeight();
        return new IntVector(x, y);
    }

    /**
     * Return whether the coordinates given holds an exit.
     *
     * @param x possibly the x coordinate of the exit
     * @param y possibly the y coordinate of the exit
     * @return whether the coordinates given holds an exit.
     */
    public boolean isExit(int x, int y) {
        if (exit == null) {
            return false;
        }
        System.out.println("player is in " + x + ", " + y);
        System.out.println("exit is in " + exit.x + ", " + exit.y);
        return x == exit.x && y == exit.y;
    }

    /**
     * Return whether the coordinates given holds a portal.
     *
     * @param x possibly the x coordinate of the portal
     * @param y possibly the y coordinate of the portal
     * @return whether the coordinates given holds a portal.
     */
    public boolean isPortal(int x, int y) {
        if (portal == null) {
            return false;
        }
        return x == portal.x && y == portal.y;
    }

    /**
     * Return whether the coordinates given holds the golden sword.
     *
     * @param x possibly the x coordinate of the golden sword
     * @param y possibly the y coordinate of the golden sword
     * @return whether the coordinates given holds an golden sword.
     */
    public boolean isGoldenSword(int x, int y) {
        if (goldenSword == null) {
            return false;
        }
        return x == goldenSword.x && y == goldenSword.y;
    }

    /**
     * Return whether the coordinates given holds a chest.
     *
     * @param x possibly the x coordinate of the exit
     * @param y possibly the y coordinate of the exit
     * @return the chest who is on the coordinates, null otherwise
     */
    public Chest isChest(int x, int y) {
        for (Chest c : chests) {
            if (c.getX() == x && c.getY() == y) {
                return c;
            }
        }
        return null;
    }

    /**
     * Delete a chest on the map
     *
     * @param c the chest to delete
     */
    public void deleteChest(Chest c) {
        chests.remove(c);
    }

    /**
     * Delete the Golden Sword on the map
     */
    public void deleteGoldenSword() {
        goldenSword = null;
    }

    /**
     * Kill a character on the map
     *
     * @param character the character to kill
     */
    public void killCharacter(Character character) {
        enemies.remove(character);
    }

    /**
     * An exit is a tile of a ladder that lead to the next floor
     */
    class Exit {
        private int x, y;
        private Image image;

        /**
         * Constructor for an exit, places it on an unoccupied floor tile
         */
        public Exit() {
            do {
                x = rand.nextInt(map.getWidth());
                y = rand.nextInt(map.getHeight());
            } while (map.getTileImage(x, y, 1) == null);
            x *= map.getTileWidth();
            y *= map.getTileHeight();
            try {
                image = new Image("/src/main/resources/img/floor_ladder.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }

        /**
         * Draws the ladder on a given graphic context
         *
         * @param g teh graphics in which the ladder has to be drawn
         */
        public void render(Graphics g) {
            g.drawImage(image, x, y);
        }
    }

    /**
     * Generates enemies on the map
     */
    private void generateEnemies() {
        try {
            for (int i = 0; i < NUMBER_OF_ENEMIES; ++i) {
                enemies.add(new Enemy(new Undead(new BadGuy()), this));
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * A chest contain random item that the player can take
     */
    public class Chest {
        @Getter
        private int x, y;
        private Image image;
        @Getter
        private Item item;

        public Chest() {
            boolean hasChest;
            do {
                hasChest = false;
                x = rand.nextInt(map.getWidth());
                y = rand.nextInt(map.getHeight());
                for (Chest c : chests) {
                    if (c.getX() == x && c.getY() == y) {
                        hasChest = true;
                    }
                }
            } while (map.getTileImage(x, y, 1) == null && !hasChest);
            x *= map.getTileWidth();
            y *= map.getTileHeight();
            try {
                image = new Image("/src/main/resources/img/chest_empty_open_anim_f0.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
            LinkedList<Item> allItems = new LinkedList<>();
            allItems.add(new Sword(index));
            allItems.add(new Staff(index));
            allItems.add(new Bow(index));
            allItems.add(new HealthPotion());
            allItems.add(new TransformPotion());

            item = allItems.get(rand.nextInt(allItems.size()));
        }

        /**
         * Draws the chest on a given graphic context
         *
         * @param g the graphics in which the chest has to be drawn
         */
        public void render(Graphics g) {
            g.drawImage(image, x, y);
        }
    }


    public class GoldenSword extends Weapon {
        private int x, y;
        private Image image;

        /**
         * Constructor for the Golden Sword, places it on an unoccupied floor tile
         */
        public GoldenSword() {
            super(11, 16, 1);

            do {
                x = rand.nextInt(map.getWidth());
                y = rand.nextInt(map.getHeight());
            } while (map.getTileImage(x, y, 1) == null);
            x *= map.getTileWidth();
            y *= map.getTileHeight();
            try {
                image = new Image("/src/main/resources/img/weapon_golden_sword.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }

        /**
         * Draws the ladder on a given graphic context
         *
         * @param g the graphics in which the ladder has to be drawn
         */
        public void render(Graphics g) {
            g.drawImage(image, x, y);
        }

        @Override
        public String toString() {
            return "sword T10";
        }
    }

    class Portal {
        @Setter
        private int x, y;
        private Image image;

        /**
         * Constructor for a portal
         */
        public Portal(int x, int y) {
            this.x = x;
            this.y = y;
            try {
                image = new Image("/src/main/resources/img/portal.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }

        /**
         * Draws the portal on a given graphic context
         *
         * @param g the graphics in which the portal has to be drawn
         */
        public void render(Graphics g) {
            g.drawImage(image, x, y);
        }
    }
}
