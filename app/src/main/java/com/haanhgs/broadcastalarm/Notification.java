package com.haanhgs.broadcastalarm;

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
    private static final int REQUEST_CODE = 1979;
    private final Context context;
    private final int id;
    private final String message;
    private final NotificationManager manager;

    public Notification(Context context, int id, String message){
        this.context = context;
        this.id = id;
        this.message = message;
        manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && manager != null){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Alert");
            channel.enableVibration(true);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            manager.createNotificationChannel(channel);
        }
    }

    private PendingIntent createIntent(){
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setAction(Intent.ACTION_MAIN);
        return PendingIntent.getActivity(
                context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private NotificationCompat.Builder createBuilder(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, CHANNEL_ID);
        builder.setAutoCancel(true);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setSmallIcon(R.drawable.running);
        builder.setContentIntent(createIntent());
        builder.setContentTitle("Alert");
        builder.setContentText(message);
        return builder;
    }

    public void createNotification(){
        createChannel();
        if (!App.isVisible() && manager != null){
            manager.notify(id, createBuilder().build());
        }
    }
}