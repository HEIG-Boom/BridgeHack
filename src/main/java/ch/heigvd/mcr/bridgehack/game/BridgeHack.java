package ch.heigvd.mcr.bridgehack.game;

import ch.heigvd.mcr.bridgehack.game.state.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class is the starting point of the game. The class must have all its states set.
 */
public class BridgeHack extends StateBasedGame {
    /**
     * Constructor that just names the game
     */
    public BridgeHack() {
        super("BridgeHack");
    }

    @Override
    public void initStatesList(GameContainer gameContainer) {
        addState(new MenuState());
        addState(new LoreState());
        addState(new GameState());
        addState(new WinState());
        addState(new LoseState());
    }
}
