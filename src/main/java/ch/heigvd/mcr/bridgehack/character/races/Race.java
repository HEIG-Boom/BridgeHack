package ch.heigvd.mcr.bridgehack.character.races;

import ch.heigvd.mcr.bridgehack.character.roles.Role;
import ch.heigvd.mcr.bridgehack.character.StatModifier;
import lombok.Getter;
import lombok.Setter;
import org.newdawn.slick.Graphics;

/**
 * Class representing a race
 * <p>
 * Provides the main interface to our bridge system
 */
public abstract class Race {
    @Getter
    @Setter
    private Role role;
    private StatModifier statModifier;

    /**
     * Simple constructor for the character's race
     *
     * @param role The role of the character
     */
    public Race(Role role, StatModifier statModifier) {
        this.role = role;
        this.statModifier = statModifier;
    }

    /**
     * Render a character
     *
     * @param g      The graphics object to draw onto
     * @param moving Whether the character is moving
     * @param x      The x component of the character's position
     * @param y      The y component of the character's position
     */
    public void render(Graphics g, boolean moving, int x, int y) {
        role.render(g, moving, x, y);
    }

    public StatModifier getStatModifier() {
        return statModifier.addModifier(role.getStatModifier());
    }
}
