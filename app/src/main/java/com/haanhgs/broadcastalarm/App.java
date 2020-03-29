package com.haanhgs.broadcastalarm;

import android.app.Application;

//return app visibility
public class App extends Application {

    private static boolean visible;

    public static boolean isVisible() {
        return visible;
    }

    public static void resume(){
        visible = true;
    }

    public static void pause(){
        visible = false;
    }

}
