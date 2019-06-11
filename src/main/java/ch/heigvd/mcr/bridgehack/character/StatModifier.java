package ch.heigvd.mcr.bridgehack.character;

import lombok.Getter;

import java.util.Objects;

/**
 * A StatModifier is a class that modifies statistics.
 */
@Getter
public class StatModifier {
    private int strength;
    private int dexterity;
    private int intelligence;

    public StatModifier() {
        this(0, 0, 0);
    }
    public StatModifier(int strength, int dexterity, int intelligence) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    /**
     * Returns a new statModifier composed by the addition of this modifier
     * with another
     * @param statModifier the statModifier to be added
     * @return a new statModifier composed by the addition of this modifier
     *         with another
     */
    public StatModifier addModifier(StatModifier statModifier) {
        return new StatModifier(strength + statModifier.strength,
                dexterity + statModifier.dexterity,
                intelligence + statModifier.intelligence);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        StatModifier that = (StatModifier) o;
        return strength == that.strength &&
                dexterity == that.dexterity &&
                intelligence == that.intelligence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strength, dexterity, intelligence);
    }
}
