package ch.heigvd.mcr.bridgehack.game;

import ch.heigvd.mcr.bridgehack.game.state.LoreState;
import ch.heigvd.mcr.bridgehack.game.state.GameState;
import ch.heigvd.mcr.bridgehack.game.state.MenuState;
import lombok.Getter;
import lombok.Setter;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class is the starting point of the game. The class must have all its states set.
 */
public class BridgeHack extends StateBasedGame {
    @Setter
    @Getter
    // attribute used to pass the username across states
    private String username = "";

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
    }
}
