package ch.heigvd.mcr.bridgehack;

import ch.heigvd.mcr.bridgehack.player.Player;
import ch.heigvd.mcr.bridgehack.player.races.Dwarf;
import ch.heigvd.mcr.bridgehack.player.races.Human;
import ch.heigvd.mcr.bridgehack.player.roles.Hunter;
import ch.heigvd.mcr.bridgehack.player.roles.Knight;
import ch.heigvd.mcr.bridgehack.player.roles.Wizard;
import org.newdawn.slick.*;
import java.awt.Font;
import java.util.LinkedList;

public class BridgeHack extends BasicGame {
    private LinkedList<Map> maps;
    private Map map;
    private Player player;
    // Temporary
    private LinkedList<Player> enemies = new LinkedList<>();

    private String notification = "";
    private boolean attacking;
    private TrueTypeFont ttf;
    private boolean turnIsOver = false;
    private int turn = 0;
    private int counter = 0;

    public BridgeHack() throws SlickException {
        super("BridgeHack");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        maps = new LinkedList<>();
        maps.add(new Map(1, false));
        maps.add(new Map(2, true));
        map = maps.get(0);
        player = new Human(new Knight(), map);
        // Temporary
        enemies.add(new Dwarf(new Wizard(), map));
        enemies.add(new Human(new Hunter(), map));

        Font font = new Font("Ubuntu Mono ", Font.PLAIN, 16);
        ttf = new TrueTypeFont(font, true);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.setColor(Color.white);
        ttf.drawString(400, 660, Integer.toString(turn));
        ttf.drawString(0, 660, player.getStatus());

        if (!notification.equals("")) {
            ttf.drawString(550, 660, notification);
        }

        g.scale(2, 2);
        map.render(1);
        map.render(2);

        map.renderObjects(g);
        player.render(g);
        // Temporary
        for (Player enemy : enemies)
            enemy.render(g);

        map.render(3);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if (!turnIsOver) {
            player.update(delta);
            // Temporary
            for (Player enemy : enemies)
                enemy.update(delta);

            if (counter++ > 14) {
                turnIsOver = true;
                counter = 0;
                turn++;
                player.stop();
                // Temporary
                for (Player enemy : enemies)
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
                    } else {
                        notification = "No stairs here";
                    }
                    break;
                }
            }
            turnIsOver = false;
        }
    }

    @Override
    public void keyReleased(int key, char c) {
    }
}
