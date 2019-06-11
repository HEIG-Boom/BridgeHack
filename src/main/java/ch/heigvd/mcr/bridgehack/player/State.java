package ch.heigvd.mcr.bridgehack.player;

import lombok.Getter;

/**
 * A State serves to represent a player's state at a given time of the game
 */
@Getter
public class State {
    @Getter
    private int strength;
    @Getter
    private int dexterity;
    @Getter
    private int intelligence;
    private int constitution;
    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;

    public State(int strength, int dexterity, int intelligence, int constitution, int health, int mana) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.constitution = constitution;
        this.health = health;
        this.mana = mana;
    }

    public State() {
        this(10, 10, 10, 10, 10, 5);
        maxHealth = health;
        maxMana = mana;
    }

    /**
     * Returns a string representing every stat of the status
     * @return a string representing every stat of the status
     */
    public String toString() {
        return  "St:" + strength + " Dx: " + dexterity + " In:" + intelligence + " Co:" + constitution + "\n" +
                "HP:" + health + "(" + maxHealth + ")" + " Pw:" + mana + "(" + maxMana + ")";
    }

    /**
     * Restores the mana back to full
     */
    public void restoreMana() {
        this.mana = maxMana;
    }

    /**
     * Restores the health back to full
     */
    public void restoreHealth() {
    }
}
