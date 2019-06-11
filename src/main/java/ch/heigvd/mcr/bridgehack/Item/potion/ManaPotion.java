package ch.heigvd.mcr.bridgehack.Item.potion;

import ch.heigvd.mcr.bridgehack.character.Character;

public class ManaPotion extends Potion {

    public ManaPotion() {
    }

    @Override
    public void drink(Character p) {
        p.restoreMana();
    }

    @Override
    public String toString() {
        return "mana potion";
    }
}
