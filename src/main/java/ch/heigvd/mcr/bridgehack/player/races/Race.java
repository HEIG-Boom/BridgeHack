package ch.heigvd.mcr.bridgehack.player.races;

import ch.heigvd.mcr.bridgehack.player.roles.Role;

/**
 * Class representing a race
 */
public abstract class Race {
    private Role role;

    /**
     * Get base image name for the instances's role
     *
     * @return The base image name for the current role
     */
    public String getBaseImageName() {
        return role.getBaseImageNameImpl();
    }
}
