package ch.heigvd.mcr.bridgehack.game;

import ch.heigvd.mcr.bridgehack.player.Enemy;
import ch.heigvd.mcr.bridgehack.Item.Item;
import ch.heigvd.mcr.bridgehack.Item.potion.HealthPotion;
import ch.heigvd.mcr.bridgehack.Item.potion.ManaPotion;
import ch.heigvd.mcr.bridgehack.Item.potion.TransformPotion;
import ch.heigvd.mcr.bridgehack.Item.weapon.Bow;
import ch.heigvd.mcr.bridgehack.Item.weapon.Staff;
import ch.heigvd.mcr.bridgehack.Item.weapon.Sword;
import ch.heigvd.mcr.bridgehack.utils.IntVector;
import lombok.Getter;
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
    private TiledMap map;
    private Exit exit;
    private Random rand;
    private int index;
    private LinkedList<Enemy> enemies = new LinkedList<>();
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
        if (!isLast) {
            exit = new Exit();
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
        for (Chest chest : chests) {
            chest.render(g);
        }
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
     * Returns the index of the map corresponding to the floor
     *
     * @return the index of the map corresponding to the floor
     */
    public int getIndex() {
        return index;
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

    public void deleteChest(Chest c) {
        chests.remove(c);
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


    public LinkedList<Enemy> getEnemies() {
        return enemies;
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
            allItems.add(new ManaPotion());
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
}
