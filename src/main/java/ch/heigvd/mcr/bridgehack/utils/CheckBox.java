package ch.heigvd.mcr.bridgehack.utils;

import lombok.Getter;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class CheckBox {
    @Getter
    private Rectangle box;
    @Getter
    private Enum type;

    public CheckBox(int dimension, int posX, int posY, Enum type) {
        box = new Rectangle(posX, posY, dimension, dimension);
        this.type = type;
    }

    public void draw(Graphics g) {
        g.draw(box);
        g.drawString(type.toString(), box.getX() + 50, box.getCenterY() - 10);
    }

    public boolean contains(float x, float y) {
        return box.contains(x, y);
    }
}
