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

        int futureX = x;
        int futureY = y;
        switch (direction) {
            case 0:
                futureY -= 16;
                moving = !(map.isCollision(x, y - 16));
                if (map.isCollision(x, y - 16)) {
                    System.out.println("ENEMY - Collision with wall - " + moving);
                }
                break;
            case 1:
                futureX -= 16;
                moving = !(map.isCollision(x - 16, y));
                if (map.isCollision(x - 16, y)) {
                    System.out.println("ENEMY - Collision with wall - " + moving);
                }
                break;
            case 2:
                futureY += 16;
                moving = !(map.isCollision(x, y + 16));
                if (map.isCollision(x, y + 16)) {
                    System.out.println("ENEMY - Collision with wall - " + moving);
                }
                break;
            case 3:
                futureX += 16;
                moving = !(map.isCollision(x + 16, y));
                if (map.isCollision(x + 16, y)) {
                    System.out.println("ENEMY - Collision with wall - " + moving);
                }
                break;
        }

        // Check collision with player
        //moving = !((futureX == playerPos.getX()) && (futureY == playerPos.getY()));
        if ((futureX == playerPos.getX()) && (futureY == playerPos.getY())) {
            moving = false;
            System.out.println("COLLISION WITH PLAYER - " + moving);
        }

        System.out.println("TURN");
        System.out.println("Player pos begin : (" + map.getPlayer().getX() + ":" + map.getPlayer().getY() + ")");
        System.out.println("Player pos end   : (" + playerPos.getX() + ":" + playerPos.getY() + ")");
        System.out.println("Enemy pos begin  : (" + x + ":" + y + ")");
        System.out.println("Enemy pos end    : (" + futureX + ":" + futureY + ")");
        System.out.println("Enemy direction  : " + direction);
        System.out.println("Enemy moving     : " + moving);
        System.out.println("----------------------");
    }

    public void receiveDamage(int damage) {
        playerState.setHealth(playerState.getHealth() - damage);
        System.out.println("Aïe ! I am an enemy and I have " + playerState.getHealth() + "hp.");
    }
}
