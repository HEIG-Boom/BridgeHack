package ch.heigvd.mcr.bridgehack.Item.potion;

import ch.heigvd.mcr.bridgehack.player.Player;
import ch.heigvd.mcr.bridgehack.player.roles.Wizard;

public class TransformPotion extends Potion {

    @Override
    public void drink(Player p) {

        p.changeRole(new Wizard());
    }

    @Override
    public String toString() {
        return "transform potion";
    }
}
