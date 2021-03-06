package ch.heigvd.mcr.bridgehack.character;

import ch.heigvd.mcr.bridgehack.game.Map;
import ch.heigvd.mcr.bridgehack.utils.IntVector;

/**
 * Class representing a player
 */
public class Player extends Character {

    /**
     * Constructor for the player.
     *
     * @param map a reference to the map for collision detection
     */
    public Player(Map map) {
        super(null, map);
    }

    /**
     * Sets the player in motion to a certain direction
     *
     * @param direction the direction in which the player has to go
     */
    public void move(int direction) {
        this.direction = direction;
        moving = true;

        IntVector futurePosition = getFuturePosition();

        // Check for collision with enemies
        if (checkForEnemy(futurePosition) != null) {
            moving = false;
        }
    }
}
