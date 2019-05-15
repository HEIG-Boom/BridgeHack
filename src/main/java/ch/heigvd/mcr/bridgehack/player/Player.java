package ch.heigvd.mcr.bridgehack.player;

import org.newdawn.slick.*;

public class Player {
    private float x, y;
    private boolean moving = false;
    private int direction = 0;
    private Animation idleAnimation = new Animation();
    private Animation runAnimation = new Animation();

    public Player() throws SlickException {
        this(0, 0);
    }

    public Player(float x, float y) throws SlickException {
        this.x = x;
        this.y = y;
        for (int i = 0; i < 4; ++i ) {
            idleAnimation.addFrame(new Image("resources/img/knight_m_idle_anim_f" + i + ".png"), 100);
            runAnimation.addFrame(new Image("resources/img/knight_m_run_anim_f" + i + ".png"), 100);
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
        if (moving || (int) x % 16 != 0 || (int) y % 16 != 0) {
            System.out.println("x is " + x + ", y is " + y);
            switch (direction) {
                case 0: y -= .05f * delta; break;
                case 1: x -= .05f * delta; break;
                case 2: y += .05f * delta; break;
                case 3: x += .05f * delta; break;
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(x, y + 8, 16, 8);

        if(moving) {
            g.drawAnimation(runAnimation, x, y - 16);
        } else {
            g.drawAnimation(idleAnimation, x, y - 16);
        }
    }
}
