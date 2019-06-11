package ch.heigvd.mcr.bridgehack.player.roles;

import org.newdawn.slick.SlickException;

public class Wizard extends Role {
    /**
     * Role constructor for the Wizard role
     */
    public Wizard() throws SlickException {
        super("wizzard_m");
    }

    @Override
    public String toString() {
        return "Wizard";
    }
}
