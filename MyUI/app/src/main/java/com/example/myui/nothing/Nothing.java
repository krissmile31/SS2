package com.example.myui.nothing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.myui.MainActivity;
import com.example.myui.R;
import com.example.myui.favourite.Favourite;

public class Nothing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nothing);
        findViewById(R.id.back).setBackgroundResource(R.color.purple_200);

        YoYo.with(Techniques.RubberBand).duration(1000).repeat(100).playOn(findViewById(R.id.textView));
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.blink_anim);
        findViewById(R.id.back).startAnimation(anim);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Nothing.this, MainActivity.class));
            }
        });
    }
}