package ch.heigvd.mcr.bridgehack.utils;

import lombok.Getter;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * This class represents box with text aside.
 */
public class CheckBox {
    @Getter
    private Rectangle box;
    @Getter
    private Enum type;

    /**
     * Constructor of checkbox
     *
     * @param dimension dimension of the square
     * @param posX      x position of the box
     * @param posY      y position of the square
     * @param type      type of enum the box represents
     */
    public CheckBox(int dimension, int posX, int posY, Enum type) {
        box = new Rectangle(posX, posY, dimension, dimension);
        this.type = type;
    }

    /**
     * Used to draw the checkbox and the text on screen
     *
     * @param g graphics where to draw
     */
    public void draw(Graphics g) {
        g.draw(box);
        g.drawString(type.toString(), box.getX() + 50, box.getY() - 5);
    }

    /**
     * Used to check if a point is in the box
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return true if the point is in the box, false if not
     */
    public boolean contains(float x, float y) {
        return box.contains(x, y);
    }
}
