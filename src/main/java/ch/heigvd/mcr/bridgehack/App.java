package ch.heigvd.mcr.bridgehack;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.io.File;

public class App {
    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File("lib/natives").getAbsolutePath());

        System.out.println("Hello Mordor");
        try {
            new AppGameContainer(new BridgeHack(), 960, 640, false).start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
