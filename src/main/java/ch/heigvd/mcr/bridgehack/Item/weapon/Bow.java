package ch.heigvd.mcr.bridgehack.Item.weapon;

import ch.heigvd.mcr.bridgehack.player.State;

public class Bow extends Weapon {
    private int quality;
    public Bow(int quality) {
        super(quality + 1, quality + 3, 5);
        this.quality = quality;
    }

    @Override
    public int attack(State state) {
        return super.attack(state) * state.getDexterity() / 10;
    }

    @Override
    public String toString() {
        return "bow T" + quality;
    }
}
