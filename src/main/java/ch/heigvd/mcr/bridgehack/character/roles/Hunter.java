package ch.heigvd.mcr.bridgehack.character.roles;

import ch.heigvd.mcr.bridgehack.character.StatModifier;
public class Hunter extends Role {
    /**
     * Role constructor for the Hunter role
     */
    public Hunter() {
        super("elf_m", new StatModifier(0, 5, 0));
    }

    @Override
    public String toString() {
        return "Hunter";
    }
}
