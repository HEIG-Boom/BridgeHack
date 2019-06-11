package ch.heigvd.mcr.bridgehack.Item.potion;

import ch.heigvd.mcr.bridgehack.player.Player;
import ch.heigvd.mcr.bridgehack.player.roles.Wizard;
import org.newdawn.slick.SlickException;

public class TransformPotion extends Potion {

    @Override
    public void drink(Player p) throws SlickException {
        p.changeRole(new Wizard());
    }

    @Override
    public String toString() {
        return "transform potion";
    }
}
