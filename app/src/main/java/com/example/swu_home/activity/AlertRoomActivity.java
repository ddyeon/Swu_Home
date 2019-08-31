package com.example.swu_home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import com.example.swu_home.R;

import androidx.appcompat.app.AppCompatActivity;

public class AlertRoomActivity extends AppCompatActivity {

    public AlertRoomActivity() {
        //Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_room);

        final Vibrator a = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {50, 3000, 50, 3000}; // 1초 진동, 0.05초 대기, 1초 진동, 0.05초 대기

        a.vibrate(pattern, 0);


        Button button = (Button)findViewById(R.id.okbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.cancel();
                Intent intent = new Intent(AlertRoomActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}


