package ch.heigvd.mcr.bridgehack;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello Mordor");
        try {
            new AppGameContainer(new BridgeHack(), 960, 640, false).start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
