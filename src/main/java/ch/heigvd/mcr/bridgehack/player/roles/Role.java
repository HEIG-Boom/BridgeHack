package ch.heigvd.mcr.bridgehack.player.roles;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Abstract class representing a role
 */
public abstract class Role {
    // Base path to image resources
    static final private String IMG_BASE_PATH = "/src/main/resources/img/";

    private static Animation idleAnimation = new Animation();
    private static Animation runAnimation = new Animation();

    /**
     * Role constructor
     *
     * @param baseImgName The base image name for this role
     */
    public Role(String baseImgName) throws SlickException {
        String imageBasePath = IMG_BASE_PATH + baseImgName;

        for (int i = 0; i < 4; ++i) {
            idleAnimation.addFrame(new Image(imageBasePath + "_idle_anim_f" + i + ".png"), 100);
            runAnimation.addFrame(new Image(imageBasePath + "_run_anim_f" + i + ".png"), 100);
        }
    }

    /**
     * Render a character
     *
     * @param g      The graphics object to draw onto
     * @param moving Whether the character is moving
     * @param x      The x component of the character's position
     * @param y      The y component of the character's position
     */
    public void render(Graphics g, boolean moving, int x, int y) {
        if (moving) {
            g.drawAnimation(runAnimation, x, y - 16);
        } else {
            g.drawAnimation(idleAnimation, x, y - 16);
        }
    }
}
