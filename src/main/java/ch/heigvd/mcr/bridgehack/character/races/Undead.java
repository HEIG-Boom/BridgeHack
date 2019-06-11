package ch.heigvd.mcr.bridgehack.player.races;

import ch.heigvd.mcr.bridgehack.player.roles.Role;

/**
 * Race specialization: Undead
 * <p>
 * This race is spcialized to enemies, in principle
 */
public class Undead extends Race {
    /**
     * Constructor for the character
     *
     * @param role the initial role of the character
     */
    public Undead(Role role) {
        super(role);
    }
}
