package ch.heigvd.mcr.bridgehack.character.races;

import ch.heigvd.mcr.bridgehack.character.roles.Role;
import ch.heigvd.mcr.bridgehack.character.StatModifier;

/**
 * Race specialization: Human
 */
public class Human extends Race {
    /**
     * Constructor for the character
     *
     * @param role the initial role of the character
     */
    public Human(Role role) {
        super(role, new StatModifier(0, -5, 5));
    }

    @Override
    public String toString() {
        return "Human";
    }

}
