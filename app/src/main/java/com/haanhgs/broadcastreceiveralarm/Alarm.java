package com.haanhgs.broadcastreceiveralarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.Toast;

/*
create alarm manager that can auto open at reboot, this in turn will call broadcast class
to display notification. add these lines to manifest, for this class can receive reboot alert

        <receiver android:name=".Alarm"
            android:enabled="true"
            android:exported="false">
            <intent-filter>
               <action android:name="android.intent.action.BOOT_COMPLETED" />
            </intent-filter>
        </receiver>

and get permission for it
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
*/

public class Alarm extends BroadcastReceiver {

    public static final int REQUEST = 1979;

    public static void createAlarm(Context context){
        Toast.makeText(context, "Set alarm", Toast.LENGTH_LONG).show();
        AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                REQUEST,
                new Intent(context, Broadcast.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        long interval = AlarmManager.INTERVAL_FIFTEEN_MINUTES/5;
        long trigger = SystemClock.elapsedRealtime();
        if (manager != null){
            manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, trigger, interval,
                    pendingIntent);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())){
            createAlarm(context);
        }
    }
}
