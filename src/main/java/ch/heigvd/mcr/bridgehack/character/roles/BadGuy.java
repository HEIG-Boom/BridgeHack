package ch.heigvd.mcr.bridgehack.character.roles;

import ch.heigvd.mcr.bridgehack.character.StatModifier;
import org.newdawn.slick.SlickException;

/**
 * A special role designed for basic enemies in the game
 */
public class BadGuy extends Role {
    /**
     * Role constructor for the Hunter role
     */
    public BadGuy() throws SlickException {
        super("knight_f", new StatModifier());
    }

    @Override
    public String toString() {
        return "BadGuy";
    }
}
