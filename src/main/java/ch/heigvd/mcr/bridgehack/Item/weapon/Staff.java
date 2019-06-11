package ch.heigvd.mcr.bridgehack.Item.weapon;

import ch.heigvd.mcr.bridgehack.player.State;

public class Staff extends Weapon {
    public Staff(int quality) {
        super(quality + 1, quality + 4, 1);
    }

    @Override
    public int attack(State state) {
        return super.attack(state) * state.getIntelligence() / 10;
    }
}
