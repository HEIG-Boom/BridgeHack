package ch.heigvd.mcr.bridgehack.player;

import ch.heigvd.mcr.bridgehack.Map;
import org.newdawn.slick.*;

public class Player {
    private float x, y;
    private boolean moving = false;
    private int direction = 0;
    private Map map;
    private Animation idleAnimation = new Animation();
    private Animation runAnimation = new Animation();

    /**
     * Constructor for the players character.
     *
     * @param map a reference to the map for collision detection
     * @param x the x starting coordinate
     * @param y the y starting coordinate
     * @throws SlickException if a problem occurred building the animations
     */
    public Player(Map map, float x, float y) throws SlickException {
        this.map = map;
        this.x = x;
        this.y = y;
        for (int i = 0; i < 4; ++i ) {
            idleAnimation.addFrame(new Image("/src/main/resources/img/knight_m_idle_anim_f" + i + ".png"), 100);
            runAnimation.addFrame(new Image("/src/main/resources/img/knight_m_run_anim_f" + i + ".png"), 100);
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
            float futureX = x, futureY = y;
            switch (direction) {
                case 0: futureY -= .06f * delta; break;
                case 1: futureX -= .06f * delta; break;
                case 2: futureY += .06f * delta; break;
                case 3: futureX += .06f * delta; break;
            }

            x = futureX;
            y = futureY;

            if(((int) x % 16) == 8 && ((int) y % 16) == 8) {
                moving = false;
            }
        } else {
            x = (float) Math.floor(x);
            y = (float) Math.floor(y);
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
}
