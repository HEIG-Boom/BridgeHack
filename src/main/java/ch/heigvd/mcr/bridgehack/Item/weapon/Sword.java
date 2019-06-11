package ch.heigvd.mcr.bridgehack.Item.weapon;

import ch.heigvd.mcr.bridgehack.character.State;

public class Sword extends Weapon {
    private int quality;
    public Sword(int quality) {
        super(quality + 1, quality + 6, 1);
        this.quality = quality;
    }

    @Override
    public int attack(State state) {
        return super.attack(state) * state.getStrength() / 10;
    }

    @Override
    public String toString() {
        return "sword T" + quality;
    }
}
