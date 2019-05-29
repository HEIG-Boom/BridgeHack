package ch.heigvd.mcr.bridgehack.player;

import ch.heigvd.mcr.bridgehack.Map;
import org.newdawn.slick.*;

public class Player {
    private float x, y;
    private boolean moving = false;
    private int direction = 0;
    private Map map;
    private Animation idleAnimation = new Animation();
    private Animation runAnimation = new Animation();

    public Player(Map map, float x, float y) throws SlickException {
        this.map = map;
        this.x = x;
        this.y = y;
        for (int i = 0; i < 4; ++i ) {
            idleAnimation.addFrame(new Image("/src/main/resources/img/knight_m_idle_anim_f" + i + ".png"), 100);
            runAnimation.addFrame(new Image("/src/main/resources/img/knight_m_run_anim_f" + i + ".png"), 100);
        }
    }

    public void move(int direction) {
        this.direction = direction;
        moving = true;
    }


    public void stop() {
        moving = false;
    }

    public void update(int delta) {
        if (moving) {
            System.out.println(x + ", " + y);
            float futureX = x, futureY = y;
            boolean collision = false;
            switch (direction) {
                case 0: collision = map.isCollision(x, y- 9);
                        futureY -= .06f * delta; break;
                case 1: collision = map.isCollision(x - 9, y);
                        futureX -= .06f * delta; break;
                case 2: collision = map.isCollision(x, y + 9);
                        futureY += .06f * delta; break;
                case 3: collision = map.isCollision(x + 9, y);
                        futureX += .06f * delta; break;
            }
            if(moving = !collision) {
                x = futureX;
                y = futureY;
            }
            if(((int) x % 16) == 8 && ((int) y % 16) == 8) {
                moving = false;
            }
        } else {
            x = (float) Math.floor(x);
            y = (float) Math.floor(y);
        }

    }

    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(x - 8, y, 16, 8);

        if(moving) {
            g.drawAnimation(runAnimation, x - 8, y - 24);
        } else {
            g.drawAnimation(idleAnimation, x - 8, y - 24);
        }
    }
}
