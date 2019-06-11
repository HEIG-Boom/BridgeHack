package ch.heigvd.mcr.bridgehack.character;

import ch.heigvd.mcr.bridgehack.game.Map;

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
    }

    @Override
    public void update(int delta) {
        if (moving) {
            int futureX = x, futureY = y;
            boolean collision = false;

            switch (direction) {
                case 0:
                    futureY -= 1;
                    collision = map.isCollisionWithEnemies(x, y - 1) || map.isCollision(x,
                            y - 1);
                    break;
                case 1:
                    futureX -= 1;
                    collision = map.isCollisionWithEnemies(x - 1, y) || map.isCollision(x - 1,
                            y);
                    break;
                case 2:
                    futureY += 1;
                    collision = map.isCollisionWithEnemies(x, y + 16) || map.isCollision(x,
                            y + 16);
                    break;
                case 3:
                    futureX += 1;
                    collision =
                            map.isCollisionWithEnemies(x + 16, y) || map.isCollision(x + 16, y);
                    break;
            }

            if (collision) {
                moving = false;
                return;
            }

            x = futureX;
            y = futureY;

            if ((x % 16) == 8 && (y % 16) == 8) {
                moving = false;
            }
        }
    }
}
