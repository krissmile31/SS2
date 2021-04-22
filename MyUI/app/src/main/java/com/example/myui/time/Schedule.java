package com.example.myui.time;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;

import com.example.myui.R;

public class Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        findViewById(R.id.addEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Schedule.this, Calendar.class));
            }
        });
        findViewById(R.id.setAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Schedule.this, Alarm.class));
            }
        });
    }
}