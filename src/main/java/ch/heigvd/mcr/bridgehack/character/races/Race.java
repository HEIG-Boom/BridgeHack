package ch.heigvd.mcr.bridgehack.character.races;

import ch.heigvd.mcr.bridgehack.character.roles.Role;

/**
 * Class representing a race
 */
public abstract class Race {
    private Role role;

    /**
     * Simple constructor for the character's race
     *
     * @param role The role of the character
     */
    public Race(Role role) {
        this.role = role;
    }

    /**
     * Get base image name for the instances's role
     *
     * @return The base image name for the current role
     */
    public String getBaseImageName() {
        return role.getBaseImageNameImpl();
    }
}
