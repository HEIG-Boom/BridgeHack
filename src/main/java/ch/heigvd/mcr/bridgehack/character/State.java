package ch.heigvd.mcr.bridgehack.character;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Objects;

/**
 * A State serves to represent a player's state at a given time of the game
 */
public class State {
    @Getter
    private int strength;
    @Getter
    private int dexterity;
    @Getter
    private int intelligence;
    @Setter
    @Getter
    private int health;
    private int maxHealth;

    private LinkedList<StatModifier> modifiers;

    public State(int strength, int dexterity, int intelligence, int health) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.health = health;

        modifiers = new LinkedList<>();
    }

    public State() {
        this(10, 10, 10, 10);
        maxHealth = health;
    }

    /**
     * Returns a string representing every stat of the status
     * @return a string representing every stat of the status
     */
    public String toString() {
        return  "St:" + getStrength() + " Dx: " + getDexterity() + " In:" + getIntelligence() + "\n" +
                "HP:" + health + "(" + maxHealth + ")";
    }

    /**
     * Restores the health back to full
     */
    public void restoreHealth() {
        health = maxHealth;
    }

    /**
     * Add a modifier to this State
     * @param modifier the modifier to be added.
     */
    public void addModifier(StatModifier modifier) {
        modifiers.add(modifier);
    }

    /**
     * Removes the first occurrence of a similar stateModifier
     * and return true if one was removed, return false otherwise
     * @param modifier the stateModifier to be deleted
     * @return true if a stateModifier was removed, false otherwise
     */
    public boolean removeModifier(StatModifier modifier) {
        for (int i = 0; i < modifiers.size(); ++i) {
            if(Objects.equals(modifiers.get(i), modifier)) {
                modifiers.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the strength of this state with modifiers into account
     * @return the strength of this state with modifiers into account
     */
    public int getStrength() {
        int finalStrength = strength;

        for (StatModifier modifier : modifiers) {
            finalStrength += modifier.getStrength();
        }
        return finalStrength;
    }

    /**
     * Returns the dexterity of this state with modifiers into account
     * @return the dexterity of this state with modifiers into account
     */
    public int getDexterity() {
        int finalDexterity = dexterity;

        for (StatModifier modifier : modifiers) {
            finalDexterity += modifier.getDexterity();
        }
        return finalDexterity;
    }

    /**
     * Returns the intelligence of this state with modifiers into account
     * @return the intelligence of this state with modifiers into account
     */
    public int getIntelligence() {
        int finalIntelligence = intelligence;

        for (StatModifier modifier : modifiers) {
            finalIntelligence += modifier.getIntelligence();
        }
        return finalIntelligence;
    }
}
