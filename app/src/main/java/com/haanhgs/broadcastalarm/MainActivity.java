package com.haanhgs.broadcastalarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tbnAlarm)
    ToggleButton tbnAlarm;

    private AlarmManager alarmManager;
    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;

    private void initManagers(){
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        pendingIntent = PendingIntent.getBroadcast(
                this,
                Alarm.REQUEST_CODE,
                new Intent(this, Broadcast.class),
                PendingIntent.FLAG_NO_CREATE);
    }

    private void cancelAlarm(){
        notificationManager.cancelAll();
        if (alarmManager != null && pendingIntent != null){
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    private void handleToggleButton(){
        tbnAlarm.setChecked(pendingIntent != null);
        tbnAlarm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                Alarm.createAlarm(MainActivity.this);
            }else {
                cancelAlarm();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initManagers();
        handleToggleButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.pause();
    }


}
