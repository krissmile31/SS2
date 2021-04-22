package com.example.myui.grids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.myui.R;

public class Blog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        findViewById(R.id.blogani).animate().alpha(1f).setDuration(2000).withEndAction(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.blogani).animate().alpha(0f);
                Intent intent = new Intent((Intent.ACTION_VIEW));
                intent.setData(Uri.parse("https://www.blogger.com/"));
                startActivity(intent);
            }
        });
    }
}