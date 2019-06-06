package ch.heigvd.mcr.bridgehack.player;

import ch.heigvd.mcr.bridgehack.Map;
import ch.heigvd.mcr.bridgehack.player.roles.Role;
import org.newdawn.slick.*;

/**
 * Class representing a player
 */
public abstract class Player {
    // Base path to image resources
    static final private String IMG_BASE_PATH = "/src/main/resources/img/";

    private int x;
    private int y;
    private boolean moving = false;
    private int direction = 0;
    private Map map;
    private Animation idleAnimation = new Animation();
    private Animation runAnimation = new Animation();

    private Role role;

    /**
     * Constructor for the players character.
     *
     * @param role the initial role of the character
     * @param map a reference to the map for collision detection
     * @param x the x starting coordinate
     * @param y the y starting coordinate
     * @throws SlickException if a problem occurred building the animations
     */
    public Player(Role role, Map map, int x, int y) throws SlickException {
        this.role = role;
        this.map = map;
        this.x = x;
        this.y = y;

        String imageBasePath = IMG_BASE_PATH + getBaseImageName();
        for (int i = 0; i < 4; ++i ) {
            idleAnimation.addFrame(new Image(imageBasePath + "_idle_anim_f" + i + ".png"), 100);
            runAnimation.addFrame(new Image(imageBasePath + "_run_anim_f" + i + ".png"), 100);
        }
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
            System.out.println(x + ", " + y);
            int futureX = x, futureY = y;
            switch (direction) {
                case 0: futureY -= .1f * delta; break;
                case 1: futureX -= .1f * delta; break;
                case 2: futureY += .1f * delta; break;
                case 3: futureX += .1f * delta; break;
            }

            x = futureX;
            y = futureY;

            if((x % 16) == 8 && (y % 16) == 8) {
                moving = false;
            }
        } else {
            x = (int) Math.floor(x);
            y = (int) Math.floor(y);
        }
    }

    /**
     * Displays the player in the graphics given
     *
     * @param g the Graphics in which it will be rendered
     */
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(x - 8, y, 16, 8);

        if(moving) {
            g.drawAnimation(runAnimation, x - 8, y - 24);
        } else {
            g.drawAnimation(idleAnimation, x - 8, y - 24);
        }
    }

    /**
     * Get base image name for the instances's role
     *
     * @return The base image name for the current role
     */
    public String getBaseImageName() {
        return role.getBaseImageNameImpl();
    }
}
