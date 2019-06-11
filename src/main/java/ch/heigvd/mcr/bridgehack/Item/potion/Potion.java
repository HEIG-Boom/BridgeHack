package ch.heigvd.mcr.bridgehack.Item.potion;

import ch.heigvd.mcr.bridgehack.Item.Item;
import ch.heigvd.mcr.bridgehack.player.Player;
import org.newdawn.slick.SlickException;

abstract public class Potion extends Item {
    abstract public void drink(Player p) throws SlickException;
}
