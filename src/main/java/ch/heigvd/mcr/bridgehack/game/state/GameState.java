package ch.heigvd.mcr.bridgehack.game.state;

import ch.heigvd.mcr.bridgehack.game.BridgeHack;
import ch.heigvd.mcr.bridgehack.game.Map;
import lombok.Getter;
import ch.heigvd.mcr.bridgehack.character.Enemy;
import ch.heigvd.mcr.bridgehack.character.Player;
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

    private BridgeHack game;

    private LinkedList<Map> maps;
    private Map map;

    @Getter
    private Player player;

    private String notification = "";
    private boolean attacking;
    private TrueTypeFont ttf;
    private boolean turnIsOver = false;
    private int turn = 0;
    private int counter = 0;
    private boolean drinking;
    private boolean equipping;
    private boolean deleting;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game = (BridgeHack) stateBasedGame;
        maps = new LinkedList<>();
        maps.add(new Map(1, false));
        maps.add(new Map(2, false));
        maps.add(new Map(3, true));
        map = maps.get(0);
        player = new Player(map);

        // Set the player to the map
        map.setPlayer(player);

        Font font = new Font("Ubuntu Mono ", Font.PLAIN, 16);
        ttf = new TrueTypeFont(font, true);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.white);
        ttf.drawString(500, 660, Integer.toString(turn));

        player.renderText(ttf);

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
            for (Enemy enemy : map.getEnemies()) {
                enemy.update(delta);
            }

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
            if (Character.isDigit(c) && drinking) {
                try {
                    if (player.drink(Character.getNumericValue(c)) == -1) {
                        notification = "You drink your weapon, bad idea...";
                    }
                    drinking = false;
                } catch (SlickException e) {
                    e.printStackTrace();
                }
            } else if (Character.isDigit(c) && equipping) {
                player.equip(Character.getNumericValue(c));
                equipping = false;
                notification = "";
            } else if (Character.isDigit(c) && deleting) {
                player.deleteItem(Character.getNumericValue(c));
                deleting = false;
                notification = "";
            }
            switch (key) {
                case Input.KEY_UP: {
                    if (attacking) {
                        player.attack(0);
                        attacking = false;
                        notification = "";
                    } else {
                        player.move(0);
                    }

                    enemyTurn();
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

                    enemyTurn();
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

                    enemyTurn();
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

                    enemyTurn();
                    break;
                }
                case Input.KEY_A: {
                    attacking = true;
                    drinking = false;
                    equipping = false;
                    deleting = false;
                    notification = "Which direction ?";
                    enemyTurn();
                    return;
                }
                case Input.KEY_D: {
                    notification = "";
                    if(map.isExit(player.getX(), player.getY())) {
                        map = maps.get(map.getIndex());
                        player.setMap(map);
                        map.setPlayer(player);
                    } else if (map.isPortal(player.getX(), player.getY()) ||
                        map.isPortal(player.getX(), player.getY()-16)) {
                        game.enterState(WinState.ID);
                    } else {
                        notification = "No stairs here";
                    }
                    enemyTurn();
                    break;
                }
                case Input.KEY_Q: {
                    drinking = true;
                    attacking = false;
                    equipping = false;
                    deleting = false;
                    notification = "Drink what ?";
                    enemyTurn();
                    break;
                }
                case Input.KEY_T: {
                    attacking = false;
                    equipping = false;
                    drinking = false;
                    deleting = false;
                    Map.Chest chest = map.isChest(player.getX(), player.getY());
                    if(chest != null && player.getInventory().size() < 10) {
                        player.giveItem(chest.getItem());
                        map.deleteChest(chest);
                    } else if(map.isGoldenSword(player.getX(), player.getY())) {
                        player.giveItem(map.getGoldenSword());
                        map.deleteGoldenSword();
                    }
                    enemyTurn();
                    break;
                }
                case Input.KEY_E: {
                    notification = "Which weapon? ";
                    equipping = true;
                    attacking = false;
                    drinking = false;
                    deleting = false;
                    enemyTurn();
                    break;
                }
                case Input.KEY_X: {
                    notification = "Which item? ";
                    equipping = false;
                    attacking = false;
                    drinking = false;
                    deleting = true;
                    enemyTurn();
                    break;
                }
            }

            turnIsOver = false;
        }
    }

    /**
     * Move all enemies on the map
     */
    private void enemyTurn() {
        // Move all enemies
        for (Enemy enemy : map.getEnemies()) {
            enemy.move();
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