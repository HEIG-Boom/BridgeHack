package ch.heigvd.mcr.bridgehack.player.roles;

import org.newdawn.slick.SlickException;

/**
 * A special role designed for basic enemies in the game
 */
public class Enemy extends Role {
    /**
     * Role constructor for the Hunter role
     */
    public Enemy() throws SlickException {
        super("big_zombie_m");
    }

    @Override
    public String toString() {
        return "Undead";
    }
}
