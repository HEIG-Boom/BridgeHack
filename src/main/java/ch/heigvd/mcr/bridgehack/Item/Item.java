package ch.heigvd.mcr.bridgehack.Item;

import org.newdawn.slick.Graphics;

abstract public class Item {
    private static int counter = 0;
    private int x, y;
    private int id;

    public Item() {
        id = counter++;
    }

    public int getId() {
        return id;
    }

    public void render(Graphics g) {
    }
}
