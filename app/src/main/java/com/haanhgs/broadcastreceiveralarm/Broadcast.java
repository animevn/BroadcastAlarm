package com.haanhgs.broadcastreceiveralarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*
must add these line into manifest.xml, inside <application, so this class can receive broadcast
        <receiver
            android:name=".Broadcast"
            android:enabled="true"
            android:exported="false">
        </receiver>

receive broadcast and create notification each  3 minutes.
*/

public class Broadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Notification notification = new Notification(context, 22, "Standup each 3 minutes");
        notification.createNotification();
    }
}
