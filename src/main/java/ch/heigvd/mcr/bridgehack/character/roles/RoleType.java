package ch.heigvd.mcr.bridgehack.character.roles;

import lombok.Getter;

public enum  RoleType {
    HUNTER("Hunter", "Always at ease in the wilderness, they are not used to the confined spaces of\n         " +
                                     "a cave. However, their agility allows them to still be powerful foes.",
                                      new Hunter()),

    KNIGHT("Knight", "A warrior in the strictest sense, their mastery of the sword is equaled by none.\n         " +
                                     "No creature wants to find themself confronted to a knight in melee combat.",
                                      new Knight()),

    WIZARD("Wizard", "Trained in great knowledge and craft, these masters of mystic arts are able\n         " +
                                     "to use magical items to their fullest potential.",
                                      new Wizard());

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

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
