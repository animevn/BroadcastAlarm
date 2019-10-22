package com.haanhgs.broadcastreceiveralarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.core.app.NotificationCompat;

public class Notification {

    private static final String CHANNEL_ID = "com.haanhgs.broadcastreceiveralarm.notify";
    private static final String CHANNEL_NAME = "com.haanhgs.broadcastreceiveralarm";


    private static void createChannel(){
        NotificationManager manager =
                (NotificationManager)App.context().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Alert");
            channel.enableVibration(true);
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            if (manager != null) manager.createNotificationChannel(channel);
        }
    }

    private static PendingIntent createIntent(){
        Intent intent = new Intent(App.context(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setAction(Intent.ACTION_MAIN);
        return PendingIntent.getActivity(App.context(), 1979, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static NotificationCompat.Builder createBuilder(String string){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(App.context(),
                CHANNEL_ID);
        builder.setAutoCancel(true);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setSmallIcon(R.drawable.running);
        builder.setContentIntent(createIntent());
        builder.setContentTitle("Alert");
        builder.setContentText(string);
        return builder;
    }

    public static void createNotification(int id, String message){
        NotificationManager manager =
                (NotificationManager)App.context().getSystemService(Context.NOTIFICATION_SERVICE);
        createChannel();
        if (!App.isAppVisible() && manager != null){
            manager.notify(id, createBuilder(message).build());
        }
    }
}
