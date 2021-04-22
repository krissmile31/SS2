package com.example.myui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.myui.mynotes.Notes;
import com.example.myui.favourite.Favourite;
import com.example.myui.grids.Blog;
import com.example.myui.grids.Entertainment;
import com.example.myui.grids.Finance;
import com.example.myui.grids.News.Gallery;
import com.example.myui.grids.Goals;
import com.example.myui.grids.Health;
import com.example.myui.grids.News.MyBookMarks;
import com.example.myui.grids.Sport;
import com.example.myui.grids.Study;
import com.example.myui.nothing.Nothing;
import com.example.myui.time.Schedule;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView navigationView;
    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        String name = getIntent().getExtras().getString("name");
        txtName.setText(name);

        navigationView = findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(MainActivity.this);

        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.direct).animate().alpha(0f).setDuration(500);
                findViewById(R.id.gif).animate().alpha(0f).setDuration(500);
                findViewById(R.id.ScrollView).animate().alpha(1f).setDuration(500);
            }
        });

        findViewById(R.id.goals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Goals.class));
            }
        });

        findViewById(R.id.finance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Finance.class));
            }
        });

        findViewById(R.id.health).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Health.class));
            }
        });

        findViewById(R.id.study).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Study.class));
            }
        });

        findViewById(R.id.sport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Sport.class));
            }
        });

        findViewById(R.id.blog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Blog.class));
            }
        });

        findViewById(R.id.gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Gallery.class));
            }
        });

        findViewById(R.id.entertainment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Entertainment.class));
            }
        });







        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Nothing.class));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.navigation_home:
//                startActivity(new Intent(this, MainActivity.class));
                return true;

            case R.id.schedule:
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                startActivity(new Intent(this, Schedule.class));
                return true;

            case R.id.favourite:
                startActivity(new Intent(MainActivity.this, Favourite.class));
                return true;

            case R.id.notes:
                startActivity(new Intent(MainActivity.this, Notes.class));
                return true;

            case R.id.account:
                startActivity(new Intent(this, MyBookMarks.class));
                return true;
        }
        return false;
    }

}