package ch.heigvd.mcr.bridgehack.character.races;

import ch.heigvd.mcr.bridgehack.character.StatModifier;
import ch.heigvd.mcr.bridgehack.character.roles.Role;

/**
 * Race specialization: Undead
 * <p>
 * This race is specialized to enemies, in principle
 */
public class Undead extends Race {
    /**
     * Constructor for the character
     *
     * @param role the initial role of the character
     */
    public Undead(Role role) {
        super(role, new StatModifier());
    }
}
