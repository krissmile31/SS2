package com.example.myui.grids.News;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myui.R;

public class Gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        findViewById(R.id.galleryani).animate().setDuration(2000).withEndAction(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Gallery.this, MyBookMarks.class));
            }
        });
    }
}