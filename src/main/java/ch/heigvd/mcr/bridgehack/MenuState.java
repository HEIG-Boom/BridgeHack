package ch.heigvd.mcr.bridgehack;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState {
    public static final int ID = 1;
    private Image background;
    private Image title;
    private BridgeHack game;
    private String username = "";

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = (BridgeHack) stateBasedGame;
        background = new Image("src/main/resources/img/menu.png");
        title = new Image("src/main/resources/img/title.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        background.draw(0, 0);
        title.draw(475, 300);
        graphics.drawString("Enter your name :", 440, 450);
        graphics.drawString(username, 600, 450);
        graphics.drawString("Press enter to face your destiny...", 440, 600);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {

    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_ENTER:
                if (!username.equals("")) {
                    game.setUsername(username);
                    game.enterState(LoreState.ID);
                }
                break;
            case Input.KEY_BACK:
                if (!username.equals("")) {
                    username = username.substring(0, username.length() - 1);
                }
                break;
            default:
                int ch = Character.getNumericValue(c);
                if (ch < 37 && ch > -1) {
                    username += c;
                }
        }
    }
}
