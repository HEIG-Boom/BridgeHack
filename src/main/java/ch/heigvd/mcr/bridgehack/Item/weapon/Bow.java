package ch.heigvd.mcr.bridgehack.Item.weapon;

import ch.heigvd.mcr.bridgehack.player.State;

public class Bow extends Weapon {
    public Bow(int quality) {
        super(quality + 1, quality + 3, 5);
    }

    @Override
    public int attack(State state) {
        return super.attack(state) * state.getDexterity() / 10;
    }
}
