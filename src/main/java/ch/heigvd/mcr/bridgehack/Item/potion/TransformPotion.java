package ch.heigvd.mcr.bridgehack.Item.potion;

import ch.heigvd.mcr.bridgehack.character.Character;
import ch.heigvd.mcr.bridgehack.character.roles.Wizard;
import org.newdawn.slick.SlickException;

public class TransformPotion extends Potion {

    @Override
    public void drink(Character p) throws SlickException {
        p.changeRole(new Wizard());
    }

    @Override
    public String toString() {
        return "transform potion";
    }
}
