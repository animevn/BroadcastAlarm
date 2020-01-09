package com.haanhgs.broadcastreceiveralarm;

public class App {

    private static boolean visible;

    public boolean isVisible() {
        return visible;
    }

    public static void resume(){
        visible = true;
    }

    public static void pause(){
        visible = false;
    }

}
