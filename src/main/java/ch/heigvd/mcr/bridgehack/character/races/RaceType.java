package ch.heigvd.mcr.bridgehack.character.races;

import lombok.Getter;

/**
 * Enum for race type
 */
public enum RaceType {
    DWARF("Dwarf",
            "A small yet strong creature from the mountain caves. Their intelligence lacks,\n        " +
                    "but their origins make them very well suited for the task at hand.",
            new Dwarf(null)),

    ELF("Elf",
            "A being so agile, they barely seem terrestrial. Their grace is incomparable\n        " +
                    "but strength is their weakness.",
            new Elf(null)),

    HUMAN("Human",
            "This warrior race has great intelligence in combat and warfare. They lack agility\n        " +
                    "though, which makes them ineffective with a bow and arrow.",
            new Human(null));

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

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
