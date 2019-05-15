package ch.heigvd.mcr.bridgehack;

import java.io.File;

public class App {
    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File("lib/natives").getAbsolutePath());

        System.out.println("Hello Mordor");
    }
}
