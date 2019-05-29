package ch.heigvd.mcr.bridgehack;

import ch.heigvd.mcr.bridgehack.player.Player;
import org.newdawn.slick.*;

public class BridgeHack extends BasicGame {
    private Map map;
    private Player player;

    public BridgeHack() throws SlickException {
        super("BridgeHack");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        map = new Map();
        player = new Player(map,40, 56);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.scale(2, 2);
        map.render();
        player.render(g);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        player.update(delta);
    }

    @Override
    public void keyPressed(int key, char c) {
        System.out.println(key + " " + c);
        switch (key) {
            case Input.KEY_UP:    player.move(0); break;
            case Input.KEY_LEFT:  player.move(1); break;
            case Input.KEY_DOWN:  player.move(2); break;
            case Input.KEY_RIGHT: player.move(3); break;
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        //player.stop();
    }
}
