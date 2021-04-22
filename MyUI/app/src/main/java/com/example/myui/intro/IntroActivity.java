package com.example.myui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myui.login.Login;
import com.example.myui.MainActivity;
import com.example.myui.R;

public class IntroActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private TextView[] nav;
    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.viewPager);
        linearLayout = findViewById(R.id.linearLayout);
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        navPage(0);
        viewPager.addOnPageChangeListener(vOnPageChangeListener);
        findViewById(R.id.startBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, Login.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, Login.class);
                startActivity(intent);
            }
        });


    }

    public void navPage(int position) {
        nav = new TextView[4];
        linearLayout.removeAllViews();
        findViewById(R.id.startBtn).animate().alpha(0f).setDuration(10);
        findViewById(R.id.skip).animate().alpha(1f).setDuration(10);
        for(int i=0; i<nav.length; i++){
            nav[i] = new TextView(this);
            nav[i].setText(Html.fromHtml("&#8226"));
            nav[i].setTextSize(35);
            nav[i].setTextColor(getResources().getColor(R.color.dots));
            linearLayout.addView(nav[i]);
            if(position == nav.length - 1) {
                findViewById(R.id.startBtn).animate().alpha(1f).setDuration(10);
                findViewById(R.id.skip).animate().alpha(0f).setDuration(10);
            }
        }
        if (nav.length > 0)
            nav[position].setTextColor(getResources().getColor(R.color.black));

    }

    ViewPager.OnPageChangeListener vOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            navPage(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };




}