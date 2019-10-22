package com.haanhgs.broadcastreceiveralarm;

import android.app.Application;
import android.content.Context;
import java.lang.ref.WeakReference;

public class App extends Application {

    private static boolean appVisible;
    private static WeakReference<Context> context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = new WeakReference<>(getApplicationContext());
    }

    public static Context context(){
        return context.get();
    }

    public static boolean isAppVisible() {
        return appVisible;
    }

    public static void onResume(){
        appVisible = true;
    }

    public static void onPause(){
        appVisible = false;
    }

}
