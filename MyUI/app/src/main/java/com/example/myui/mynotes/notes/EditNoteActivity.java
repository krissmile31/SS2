package com.example.myui.mynotes.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myui.R;
import com.example.myui.mynotes.notes.db.NoteManager;
import com.example.myui.mynotes.notes.models.Note;

public class EditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.tut7.mynotes.EXTRA_TEXT";

    private EditText editNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        NoteManager noteManager = NoteManager.getInstance(this);
        editNote = findViewById(R.id.editNote);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_TEXT)){
            editNote.setText(intent.getStringExtra(EXTRA_TEXT));
        }

        findViewById(R.id.notok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note(editNote.getText().toString());
//                Intent intent = new Intent();
//                intent.putExtra("EDIT_FRIEND", note);
                noteManager.update(note);
                noteManager.add(note);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}