package ch.heigvd.mcr.bridgehack.Item.weapon;

import ch.heigvd.mcr.bridgehack.player.State;

public class Sword extends Weapon {
    public Sword(int quality) {
        super(quality + 1, quality + 6, 1);
    }

    @Override
    public int attack(State state) {
        return super.attack(state) * state.getStrength() / 10;
    }
}
