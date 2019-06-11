package ch.heigvd.mcr.bridgehack.Item.weapon;

import ch.heigvd.mcr.bridgehack.character.State;

public class Staff extends Weapon {
    private int quality;
    public Staff(int quality) {
        super(quality + 1, quality + 4, 1);
        this.quality = quality;
    }

    @Override
    public int attack(State state) {
        return super.attack(state) * state.getIntelligence() / 10;
    }

    @Override
    public String toString() {
        return "staff T" + quality;
    }
}
