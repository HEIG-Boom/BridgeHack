package ch.heigvd.mcr.bridgehack.character.races;

import ch.heigvd.mcr.bridgehack.character.roles.Role;

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
        super(role);
    }

    @Override
    public String toString() {
        return "Elf";
    }

}
