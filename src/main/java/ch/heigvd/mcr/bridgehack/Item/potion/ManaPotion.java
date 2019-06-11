package ch.heigvd.mcr.bridgehack.Item.potion;

import ch.heigvd.mcr.bridgehack.player.Player;

public class ManaPotion extends Potion {

    public ManaPotion() {
    }

    @Override
    public void drink(Player p) {
        p.restoreMana();
    }

    @Override
    public String toString() {
        return "mana potion";
    }
}
