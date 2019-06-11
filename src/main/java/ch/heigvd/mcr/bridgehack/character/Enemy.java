package ch.heigvd.mcr.bridgehack.character;

import ch.heigvd.mcr.bridgehack.character.races.Race;
import ch.heigvd.mcr.bridgehack.game.Map;
import ch.heigvd.mcr.bridgehack.utils.IntVector;
import org.newdawn.slick.SlickException;

import java.util.Random;

/**
 * Class representing an enemy
 */
public class Enemy extends Character {
    private Random rand;

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
        System.out.println("TURN");
        int futurePlayerX = map.getPlayer().getX();
        int futurePlayerY = map.getPlayer().getY();

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

        // Check if the Manhattan distance between the enemy and the player is less than 3
        IntVector playerPos = new IntVector(futurePlayerX, futurePlayerY);
        System.out.println("Player pos : (" + futurePlayerX + ":" + futurePlayerY + ")");
        IntVector enemyPos = new IntVector(getX(), getY());
        System.out.println("Enemy pos : (" + getX() + ":" + getY() + ")");
        IntVector distance = playerPos.manhattan(enemyPos);

        System.out.println("Distance X: " + distance.getX() / 16);
        System.out.println("Distance Y: " + distance.getY() / 16);
        System.out.println("----------------------");
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
        System.out.println("Enemy direction : " + direction);
    }

    @Override
    public void update(int delta) {
        if (moving) {
            int futureX = x, futureY = y;
            boolean collision = false;

            switch (direction) {
                case 0:
                    futureY -= 1;
                    collision = map.isCollisionWithPlayer(x, y - 1) || map.isCollision(x ,
                            y - 1);
                    break;
                case 1:
                    futureX -= 1;
                    collision = map.isCollisionWithPlayer(x - 1, y) || map.isCollision(x - 1, y);
                    break;
                case 2:
                    futureY += 1;
                    collision = map.isCollisionWithPlayer(x, y + 16) || map.isCollision(x,
                            y + 16);
                    break;
                case 3:
                    futureX += 1;
                    collision = map.isCollisionWithPlayer(x + 16, y) || map.isCollision(x + 16
                            , y);
                    break;
            }

            if (collision) {
                moving = false;
                //attack(direction);
                System.out.println("Enemy attacking");
                return;
            }

            x = futureX;
            y = futureY;

            if ((x % 16) == 8 && (y % 16) == 8) {
                moving = false;
            }
        }
    }

    public void receiveDamage(int damage) {
        playerState.setHealth(playerState.getHealth() - damage);
        System.out.println("Aïe ! I am an enemy and I have " + playerState.getHealth() + "hp.");
    }
}
