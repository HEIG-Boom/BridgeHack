package ch.heigvd.mcr.bridgehack.character.races;

import ch.heigvd.mcr.bridgehack.character.roles.Role;
import ch.heigvd.mcr.bridgehack.character.StatModifier;

/**
 * Race specialization: Elf
 */
public class Elf extends Race {
    /**
     * Constructor for the character
     *
     * @param role the initial role of the character
     */
    public Elf(Role role) {
        super(role, new StatModifier(-5, 5, 0));
    }

    @Override
    public String toString() {
        return "Elf";
    }

}
