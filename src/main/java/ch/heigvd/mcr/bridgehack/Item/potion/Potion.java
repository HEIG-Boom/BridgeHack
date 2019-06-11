package ch.heigvd.mcr.bridgehack.Item.potion;

import ch.heigvd.mcr.bridgehack.Item.Item;
import ch.heigvd.mcr.bridgehack.player.Player;

abstract public class Potion extends Item {
    abstract public void drink(Player p);
}
