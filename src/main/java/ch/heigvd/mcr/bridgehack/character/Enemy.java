package ch.heigvd.mcr.bridgehack.character;

import ch.heigvd.mcr.bridgehack.character.races.Race;
import ch.heigvd.mcr.bridgehack.game.Map;
import ch.heigvd.mcr.bridgehack.utils.IntVector;
import lombok.Setter;
import org.newdawn.slick.SlickException;

import java.util.Random;

/**
 * Class representing an enemy
 */
public class Enemy extends Character {
    private Random rand;
    @Setter
    private Player player;

    /**
     * Constructor for the enemy.
     *
     * @param race the initial race of the enemy
     * @param map  a reference to the map for collision detection
     * @throws SlickException if a problem occurred building the animations
     */
    public Enemy(Race race, Map map) throws SlickException {
        super(race, map);
        rand = new Random();
    }

    /**
     * Sets the enemy in motion to a certain direction
     */
    public void move() {
        moving = true;
        int futurePlayerX = map.getPlayer().getX();
        int futurePlayerY = map.getPlayer().getY();

        // Get future player position
        if (map.getPlayer().moving) {
            switch (map.getPlayer().getDirection()) {
                case 0:
                    futurePlayerY -= 16;
                    break;
                case 1:
                    futurePlayerX -= 16;
                    break;
                case 2:
                    futurePlayerY += 16;
                    break;
                case 3:
                    futurePlayerX += 16;
                    break;
            }
        }

        IntVector playerPos = new IntVector(futurePlayerX, futurePlayerY);
        IntVector enemyPos = new IntVector(getX(), getY());
        IntVector distance = playerPos.manhattan(enemyPos);

        // Check if the Manhattan distance between the enemy and the player is less than 3
        if (((distance.getX() / 16) + (distance.getY() / 16)) <= 3) {
            // Create a direction to approach the player
            if (distance.getY() >= distance.getX()) {
                // Get closer on y axis
                direction = playerPos.getY() < getY() ? 0 : 2;
            } else {
                // Get closer on y axis
                direction = playerPos.getX() < getX() ? 1 : 3;
            }
        } else {
            // Create random direction
            direction = rand.nextInt(4);
        }

        IntVector futurePosition = getFuturePosition();

        // Check collision with player
        if ((futurePosition.getX() == playerPos.getX()) && (futurePosition.getY() == playerPos.getY())) {
            moving = false;
            attack(direction);
        }
        // TODO if collision with player, we should attack him ?
    }

    public void attack(int direction) {
        player.receiveDamage(rand.nextInt(map.getIndex()+1));
    }

}
