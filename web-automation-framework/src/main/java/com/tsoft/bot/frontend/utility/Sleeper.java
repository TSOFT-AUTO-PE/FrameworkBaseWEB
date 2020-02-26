package com.tsoft.bot.frontend.utility;

public class Sleeper {

    private static final double escala = 1.5; // cloud

    public static void Sleep(int tiempo) {
        try {
            if (tiempo <= 0) return;
            Thread.sleep((long) (tiempo * escala));
        } catch (InterruptedException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}