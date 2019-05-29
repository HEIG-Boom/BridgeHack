package ch.heigvd.mcr.bridgehack;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
    private TiledMap map;

    public Map() {
        try {
            this.map = new TiledMap("/src/main/resources/maps/map.tmx");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public boolean isCollision(float x, float y) {
        int tileW = map.getTileWidth();
        int tileH = map.getTileHeight();
        int logicLayer = map.getLayerIndex("logic");
        Image tile = map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
        boolean collision = tile != null;
        if (collision) {
            Color color = tile.getColor((int) x % tileW, (int) y % tileH);
            collision = color.getAlpha() > 0;
        }
        return collision;
    }

    public void render() {
        map.render(0, 0);
    }
}
