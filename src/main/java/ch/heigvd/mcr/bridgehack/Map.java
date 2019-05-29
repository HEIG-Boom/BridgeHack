package ch.heigvd.mcr.bridgehack;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
    private TiledMap map;

    /**
     * General constructor for a basic map
     */
    public Map() {
        try {
            this.map = new TiledMap("/src/main/resources/maps/map.tmx");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renders the map
     */
    public void render() {
        map.render(0, 0);
    }
}
