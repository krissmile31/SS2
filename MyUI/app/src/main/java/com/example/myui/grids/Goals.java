package com.example.myui.grids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myui.MainActivity;
import com.example.myui.R;
import com.example.myui.nothing.Nothing;

public class Goals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        findViewById(R.id.goalsani).animate().alpha(1f).setDuration(2000).withEndAction(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.goalsani).animate().alpha(0f);
            }
        });
    }
}