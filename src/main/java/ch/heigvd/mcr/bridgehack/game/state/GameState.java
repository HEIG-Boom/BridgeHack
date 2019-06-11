package ch.heigvd.mcr.bridgehack.game.state;

import ch.heigvd.mcr.bridgehack.game.Map;
import ch.heigvd.mcr.bridgehack.character.Enemy;
import ch.heigvd.mcr.bridgehack.character.Player;
import ch.heigvd.mcr.bridgehack.character.races.Human;
import ch.heigvd.mcr.bridgehack.character.roles.Knight;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.util.LinkedList;

/**
 * This class represents a state of the game.
 * In this state, the player can explore the game.
 */
public class GameState extends BasicGameState {
    public static final int ID = 3;

    private LinkedList<Map> maps;
    private Map map;
    private Player player;

    private String notification = "";
    private boolean attacking;
    private TrueTypeFont ttf;
    private boolean turnIsOver = false;
    private int turn = 0;
    private int counter = 0;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        maps = new LinkedList<>();
        maps.add(new Map(1, false));
        maps.add(new Map(2, true));
        map = maps.get(0);

        player = new Player(new Human(new Knight()), map);

        // Set the player to the map
        map.setPlayer(player);

        Font font = new Font("Ubuntu Mono ", Font.PLAIN, 16);
        ttf = new TrueTypeFont(font, true);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.white);
        ttf.drawString(400, 660, Integer.toString(turn));
        ttf.drawString(0, 660, player.getStatus());

        if (!notification.equals("")) {
            ttf.drawString(550, 660, notification);
        }

        graphics.scale(2, 2);
        map.render(1);
        map.render(2);

        map.renderObjects(graphics);
        player.render(graphics);

        for (Enemy enemy : map.getEnemies())
            enemy.render(graphics);

        map.render(3);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        if (!turnIsOver) {
            player.update(delta);
            // Temporary
            for (Enemy enemy : map.getEnemies())
                enemy.update(delta);

            if (counter++ > 14) {
                turnIsOver = true;
                counter = 0;
                turn++;
                player.stop();
                for (Enemy enemy : map.getEnemies())
                    enemy.stop();
            }
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if (turnIsOver) {
            switch (key) {
                case Input.KEY_UP: {
                    if (attacking) {
                        player.attack(0);
                        attacking = false;
                        notification = "";
                    } else {
                        player.move(0);
                    }

                    break;
                }
                case Input.KEY_LEFT: {
                    if (attacking) {
                        player.attack(1);
                        attacking = false;
                        notification = "";
                    } else {
                        player.move(1);
                    }

                    break;
                }
                case Input.KEY_DOWN: {
                    if (attacking) {
                        player.attack(2);
                        attacking = false;
                        notification = "";
                    } else {
                        player.move(2);
                    }

                    break;
                }
                case Input.KEY_RIGHT: {
                    if (attacking) {
                        player.attack(3);
                        attacking = false;
                        notification = "";
                    } else {
                        player.move(3);
                    }

                    break;
                }
                case Input.KEY_A: {
                    attacking = true;
                    notification = "Which direction ?";
                    return;
                }
                case Input.KEY_D: {
                    if(map.isExit(player.getX(), player.getY())) {
                        map = maps.get(map.getIndex());
                        player.setMap(map);
                        map.setPlayer(player);
                    } else {
                        notification = "No stairs here";
                    }
                    break;
                }
            }

            // Move all enemies
            for (Enemy enemy : map.getEnemies()) {
                enemy.move();
            }

            turnIsOver = false;
        }
    }

    @Override
    public void keyReleased(int key, char c) {
    }

    @Override
    public int getID() {
        return ID;
    }
}