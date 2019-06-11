package ch.heigvd.mcr.bridgehack.character;

//import ch.heigvd.mcr.bridgehack.Item.Item;

import ch.heigvd.mcr.bridgehack.game.Map;
import ch.heigvd.mcr.bridgehack.character.races.Race;
import ch.heigvd.mcr.bridgehack.utils.IntVector;
import lombok.Getter;
import lombok.Setter;
import org.newdawn.slick.*;

/**
 * Class representing a character
 */
public abstract class Character {
    // Base path to image resources
    static final private String IMG_BASE_PATH = "/src/main/resources/img/";

    @Setter
    private String name;
    private State playerState;
    @Getter
    protected int x;
    @Getter
    protected int y;
    protected boolean moving = false;
    @Getter
    protected int direction = 0;
    protected Map map;
    //    private LinkedList<Item> inventory;
    private Animation idleAnimation = new Animation();
    private Animation runAnimation = new Animation();

    private Race race;

    /**
     * Constructor for the character.
     *
     * @param race the initial race of the character
     * @param map  a reference to the map for collision detection
     * @throws SlickException if a problem occurred building the animations
     */
    public Character(Race race, Map map) throws SlickException {
        this.race = race;
        this.map = map;
        setRandomPos();

        String imageBasePath = IMG_BASE_PATH + race.getBaseImageName();
        playerState = new State();
//        inventory = new LinkedList<>();
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
        return name + " " + playerState.toString();
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
     * Resotres a certain amount of mana to the player
     *
     * @param mana amount of mana to restore
     */
    public void restoreMana(int mana) {
        playerState.restoreMana(mana);
    }

    /**
     * Refreshes the position of the character if it's moving.
     * Should also check for collisions, combat and interact
     * with items and environment
     *
     * @param delta the time elapsed since the last tick
     */
    public abstract void update(int delta);
}
