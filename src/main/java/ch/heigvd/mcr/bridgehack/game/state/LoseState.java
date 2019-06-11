package ch.heigvd.mcr.bridgehack.game.state;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class represents the end panel in case of lose.
 */
public class LoseState extends BasicGameState {
    public static final int ID = 5;

    private final String endMessage = "Oh no .... You seem to be dead!\n\n\n" +
            "A fairy tale will want each fallen hero to be remembered but this is\n" +
            "not a fairy tale. This is BridgeHack!\n\n" +
            "No one will sing your adventure but if you want you can try again.";

    private final String quitMessage = "Press enter to quit the game. See You soon in BridgeHack.";
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
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
