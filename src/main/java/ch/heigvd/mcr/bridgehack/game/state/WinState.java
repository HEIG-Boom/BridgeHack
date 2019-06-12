package ch.heigvd.mcr.bridgehack.game.state;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class represents the end panel in case of win.
 */
public class WinState extends BasicGameState {
    public static final int ID = 4;

    private final String endMessage = "Congratulations!\n\n\n" +
            "You found the golden sword of Tatallias the Great.\n\n" +
            "The legend says the sword bearer will be rewarded by the mighty professor D.\n\n" +
            "But who knows if he will be there...";

    private final String quitMessage = "Press enter to quit the game. See you soon in BridgeHack.";

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.drawString(endMessage, 200, 50);
        graphics.drawString(quitMessage, 200, 500);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ENTER) {
            System.exit(0);
        }
    }

    @Override
    public int getID() {
        return ID;
    }
}
