package ch.heigvd.mcr.bridgehack;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello Mordor");
        try {
            AppGameContainer app = new AppGameContainer(new BridgeHack(), 1200, 700, false);
            app.setTargetFrameRate(60);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
