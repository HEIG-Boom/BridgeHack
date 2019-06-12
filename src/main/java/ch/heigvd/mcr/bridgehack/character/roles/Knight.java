package ch.heigvd.mcr.bridgehack.character.roles;

import ch.heigvd.mcr.bridgehack.character.StatModifier;

/**
 * Class representing the knight role
 */
public class Knight extends Role {
    /**
     * Role constructor for the Knight role
     */
    public Knight() {
        super("knight_m", new StatModifier(5, 0, 0));
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
