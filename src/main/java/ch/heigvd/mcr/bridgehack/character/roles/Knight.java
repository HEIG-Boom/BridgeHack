package ch.heigvd.mcr.bridgehack.character.roles;

import org.newdawn.slick.SlickException;

public class Knight extends Role {

    /**
     * Role constructor for the Knight role
     */
    public Knight() throws SlickException {
        super("knight_m");
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
