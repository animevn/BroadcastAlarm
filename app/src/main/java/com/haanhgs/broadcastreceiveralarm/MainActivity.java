package com.haanhgs.broadcastreceiveralarm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
