package ch.heigvd.mcr.bridgehack;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
    private TiledMap map;

    /**
     * General constructor for a basic map
     */
    public Map() {
        try {
            this.map = new TiledMap("/src/main/resources/maps/map1.tmx");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public boolean isCollision(int x, int y) {
        Image tile = map.getTileImage(x / map.getTileWidth(), y / map.getTileHeight(), 0);
        return tile != null;
    }

    /**
     * Renders the map
     */
    public void render() {
        map.render(0, 0);
    }

    /**
     * Renders only a layer of the map
     *
     * @param layer the layer to be rendered.
     */
    public void render(int layer) {
        map.render(0, 0, layer);
    }
}
