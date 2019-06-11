package ch.heigvd.mcr.bridgehack.Item.potion;

import ch.heigvd.mcr.bridgehack.character.Character;
import ch.heigvd.mcr.bridgehack.character.roles.RoleType;
import org.newdawn.slick.SlickException;

public class TransformPotion extends Potion {

    @Override
    public void drink(Character p) throws SlickException {
        p.changeRole(RoleType.randomRole().getRole());
    }

    @Override
    public String toString() {
        return "transform potion";
    }
}
