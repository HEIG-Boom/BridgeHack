package ch.heigvd.mcr.bridgehack.player.roles;

import lombok.Getter;

public enum  RoleType {
    HUNTER("Hunter", "", new Hunter()),
    KNIGHT("Knight", "", new Knight()),
    WIZARD("Wizard", "", new Wizard());

    private final String name;
    private final String description;
    @Getter
    private final Role role;

    /**
     * Constructor defining name of different roles.
     *
     * @param description description of the role
     */
    RoleType(String name, String description, Role role) {
        this.name = name;
        this.description = description;
        this.role = role;
    }

    public String toString() {
        return name + " - " + description;
    }
}
