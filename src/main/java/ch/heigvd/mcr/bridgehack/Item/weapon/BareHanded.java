package ch.heigvd.mcr.bridgehack.Item.weapon;

import ch.heigvd.mcr.bridgehack.character.State;

public class BareHanded extends Weapon {
    public BareHanded() {
        super(1, 2, 1);
    }

    @Override
    public int attack(State state) {
        return super.attack(state) * state.getStrength() / 10;
    }
}
