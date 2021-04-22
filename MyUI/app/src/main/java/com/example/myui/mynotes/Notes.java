package com.example.myui.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.myui.R;
import com.example.myui.mynotes.notes.NoteActivity;

public class Notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynotes);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.blink_anim);

        findViewById(R.id.myNotes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notes.this, NoteActivity.class));
            }
        });
    }
}