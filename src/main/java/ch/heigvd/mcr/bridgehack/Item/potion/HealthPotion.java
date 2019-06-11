package ch.heigvd.mcr.bridgehack.Item.potion;

import ch.heigvd.mcr.bridgehack.player.Player;

public class HealthPotion extends Potion {
    public HealthPotion() {
    }

    @Override
    public void drink(Player p) {
        p.restoreHealth();
    }

    @Override
    public String toString() {
        return "health potion";
    }
}
