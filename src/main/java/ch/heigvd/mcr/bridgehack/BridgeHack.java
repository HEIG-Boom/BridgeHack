package ch.heigvd.mcr.bridgehack;

import lombok.Getter;
import lombok.Setter;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


public class BridgeHack extends StateBasedGame {
    @Setter
    @Getter
    private String username = "";

    public BridgeHack() {
        super("BridgeHack");
    }

    @Override
    public void initStatesList(GameContainer gameContainer) {
        addState(new MenuState());
        addState(new LoreState());
        addState(new MapState());
    }
}
