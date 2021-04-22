package com.example.myui.time;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TimePicker;

import com.example.myui.R;

import java.util.Timer;
import java.util.TimerTask;

public class Alarm extends AppCompatActivity {
    private TimePicker time;
    private TextClock text;
    private Button setAlarmTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        time = findViewById(R.id.time);
        text = findViewById(R.id.text);
        setAlarmTo = findViewById(R.id.setAlarmTo);

        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));

        Timer t = new Timer();
        setAlarmTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Integer hour = time.getCurrentHour();
                //                Integer minute = time.getCurrentMinute();
                //
//                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
//                    //                intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
//                    //                intent.putExtra(AlarmClock.EXTRA_MINUTES, minute);
//                    startActivity(intent);


                if (text.getText().toString().equals(AlarmTime())) {
                    findViewById(R.id.alarmTo).animate().alpha(1f);
                    findViewById(R.id.cancelAlarm).animate().alpha(1f);
                    setAlarmTo.animate().alpha(0f);
                    time.animate().alpha(0f);
                    text.animate().alpha(0f);
                    ringtone.play();

                    findViewById(R.id.cancelAlarm).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            findViewById(R.id.alarmTo).animate().alpha(0f);
                            findViewById(R.id.cancelAlarm).animate().alpha(0f);
                            setAlarmTo.animate().alpha(1f);
                            time.animate().alpha(1f);
                            text.animate().alpha(1f);
                            ringtone.stop();
                        }
                    });
                }
            }
        });
////        t.scheduleAtFixedRate(new TimerTask() {
////            @Override
////            public void run() {
////                if (text.getText().toString().equals(AlarmTime())) {
////                    ringtone.play();
////                    findViewById(R.id.cancelAlarm).animate().alpha(1f);
////                    findViewById(R.id.cancelAlarm).setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View view) {
////
////                            ringtone.stop();
////                        }
////                    });
////                }
//            }}, 0, 5);

    }

    public String AlarmTime(){
        Integer hour = time.getCurrentHour();
        Integer minute = time.getCurrentMinute();
        String minutes;

        if (minute<10){
            minutes="0";
            minutes = minutes.concat(minute.toString());
        }
        else {
            minutes = minute.toString();
        }

        String alarm;
        if (hour > 12){
            hour = hour -12;
            alarm = hour.toString().concat(":").concat(minutes).concat(" PM");
        }
        else {
            alarm = hour.toString().concat(":").concat(minutes).concat(" AM");
        }

        return alarm;
    }

}