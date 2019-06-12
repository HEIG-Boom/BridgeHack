package ch.heigvd.mcr.bridgehack.character.roles;

import ch.heigvd.mcr.bridgehack.character.StatModifier;

/**
 * Class representing the wizard role
 */
public class Wizard extends Role {
    /**
     * Role constructor for the Wizard role
     */
    public Wizard() {
        super("wizzard_m", new StatModifier(0, 0, 5));
    }

    @Override
    public String toString() {
        return "Wizard";
    }
}
