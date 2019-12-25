package com.haanhgs.broadcastreceiveralarm;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton tbnAlarm;
    private AlarmManager alarmManager;
    private NotificationManager notificationManager;

    private void initViews(){
        tbnAlarm = findViewById(R.id.tbnAlarm);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    private void cancelAlarm(){
        notificationManager.cancelAll();
        if (alarmManager != null){
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, Alarm.REQUEST,
                    new Intent(this, Broadcast.class), PendingIntent.FLAG_NO_CREATE);
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    private void handleButton(){
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, Alarm.REQUEST,
                new Intent(this, Broadcast.class), PendingIntent.FLAG_NO_CREATE);
        boolean isPendingIntent = pendingIntent != null;
        tbnAlarm.setChecked(isPendingIntent);
        tbnAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Alarm.createAlarm(MainActivity.this);
                }else {
                    cancelAlarm();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        handleButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.onPause();
    }
}
