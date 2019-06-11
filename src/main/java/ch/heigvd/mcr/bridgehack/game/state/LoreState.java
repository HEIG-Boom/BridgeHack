package ch.heigvd.mcr.bridgehack.game.state;

import ch.heigvd.mcr.bridgehack.game.BridgeHack;
import ch.heigvd.mcr.bridgehack.player.races.Race;
import ch.heigvd.mcr.bridgehack.player.races.RaceType;
import ch.heigvd.mcr.bridgehack.player.roles.Role;
import ch.heigvd.mcr.bridgehack.player.roles.RoleType;
import ch.heigvd.mcr.bridgehack.utils.CheckBox;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;

/**
 * This class represents the second state of the game.
 * In this state, the history and the aim of the game are explained.
 * Finally, the player has to choose a race and a role.
 */
public class LoreState extends BasicGameState {
    public static final int ID = 2;

    private BridgeHack game;

    private final String title = "Welcome in BridgeHack";
    private final String lore = "Your adventure starts here.\n" +
            "Your destiny is to find the lost item somewhere in this dungeon.\n" +
            "Unfortunately, it seems like your journey will not be easy... The item is on the third level\n" +
            "of the dungeon. When you find it, you will be able to get out thanks to a portal.\n" +
            "You will have to fight against many creatures.";
    private final String choose = "It's time to make your choice. Please select a race and a role.";

    // Used to set the size of the checkBox and the position
    private final int checkBoxSize = 32;
    private final int initialX = 20;
    private final int initialY = 300;
    private LinkedList<CheckBox> races = new LinkedList<>();
    private LinkedList<CheckBox> roles = new LinkedList<>();
    private Image cross;
    private int selectedRace = 0;
    private int selectedRole = 0;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game = (BridgeHack) stateBasedGame;
        int boxCount = 0;
        for (RaceType raceType : RaceType.values()) {
            int y = initialY + 50 * boxCount++;
            races.add(new CheckBox(checkBoxSize, initialX, y, raceType));
        }
        for (RoleType roleType : RoleType.values()) {
            int y = initialY + 50 * ++boxCount;
            roles.add(new CheckBox(checkBoxSize, initialX, y, roleType));
        }
        cross = new Image("src/main/resources/img/cross.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        graphics.drawString(title, 0, 50);
        String playerName = ((GameState) game.getState(GameState.ID)).getPlayer().getName();
        graphics.drawString( playerName + "!", 200, 50);
        graphics.drawString(lore, 0, 80);
        graphics.drawString(choose, 0, 250);

        for (CheckBox checkBox : races) {
            checkBox.draw(graphics);
        }
        graphics.drawImage(cross, races.get(selectedRace).getBox().getX(), races.get(selectedRace).getBox().getY());

        for (CheckBox checkBox : roles) {
            checkBox.draw(graphics);
        }
        graphics.drawImage(cross, roles.get(selectedRole).getBox().getX(), roles.get(selectedRole).getBox().getY());

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {
        Input input = gameContainer.getInput();

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            for (CheckBox checkBox : races) {
                if (checkBox.contains(input.getMouseX(), input.getMouseY())) {
                    selectedRace = checkBox.getType().ordinal();
                }
            }
            for (CheckBox checkBox : roles) {
                if (checkBox.contains(input.getMouseX(), input.getMouseY())) {
                    selectedRole = checkBox.getType().ordinal();
                }
            }
        }
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void keyReleased(int key, char c) {
        if (key == Input.KEY_ENTER) {
            Role role = ((RoleType) roles.get(selectedRole).getType()).getRole();
            Race race = ((RaceType) races.get(selectedRace).getType()).getRace();

            System.out.println("salut");
            game.enterState(GameState.ID);
        }
    }
}
