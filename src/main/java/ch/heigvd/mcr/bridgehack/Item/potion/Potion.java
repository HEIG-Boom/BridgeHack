package ch.heigvd.mcr.bridgehack.Item.potion;

import ch.heigvd.mcr.bridgehack.Item.Item;
import ch.heigvd.mcr.bridgehack.character.Character;
import org.newdawn.slick.SlickException;

abstract public class Potion extends Item {
    abstract public void drink(Character p) throws SlickException;
}
