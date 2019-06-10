package ch.heigvd.mcr.bridgehack.player.races;

import ch.heigvd.mcr.bridgehack.Map;
import ch.heigvd.mcr.bridgehack.player.Player;
import ch.heigvd.mcr.bridgehack.player.roles.Role;
import org.newdawn.slick.SlickException;

/**
 * Race specialization: Elf
 */
public class Elf extends Player {
    /**
     * Constructor for the players character.
     *
     * @param role the initial role of the character
     * @param map  a reference to the map for collision detection
     * @throws SlickException if a problem occurred building the animations
     */
    public Elf(Role role, Map map) throws SlickException {
        super(role, map);
    }
}