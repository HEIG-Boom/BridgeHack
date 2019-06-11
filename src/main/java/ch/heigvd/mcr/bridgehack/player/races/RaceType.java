package ch.heigvd.mcr.bridgehack.player.races;

import lombok.Getter;

/**
 * Enum for race type
 */
public enum RaceType {
    DWARF("Dwarf", "", new Dwarf(null)),
    ELF("Elf", "", new Elf(null)),
    HUMAN("Human", "", new Human(null));

    @Getter
    private final String name;
    private final String description;
    @Getter
    private final Race race;

    /**
     * Constructor defining name of different races.
     *
     * @param description description of the race
     */
    RaceType(String name, String description, Race race) {
        this.name = name;
        this.description = description;
        this.race = race;
    }

    public String toString() {
        return name + " - " + description;
    }
}
