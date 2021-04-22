package com.example.myui.grids.News;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.myui.R;

public class MyBookMarks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookmarks);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = new BookmarksFragment();
        manager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}