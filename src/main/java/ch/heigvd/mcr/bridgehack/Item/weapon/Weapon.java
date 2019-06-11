package ch.heigvd.mcr.bridgehack.Item.weapon;

import ch.heigvd.mcr.bridgehack.Item.Item;
import ch.heigvd.mcr.bridgehack.character.State;

import java.util.Random;

abstract public class Weapon extends Item {
    private static Random rand;
    private int range;
    private int minRoll;
    private int maxRoll;

    public Weapon(int minRoll, int maxRoll, int range) {
        rand = new Random();
        this.minRoll = minRoll;
        this.maxRoll = maxRoll;
        this.range = range;
    }

    public int attack(State state) {
        return rand.nextInt(maxRoll) % minRoll;
    }

    public int getRange() {
        return range;
    }
}
