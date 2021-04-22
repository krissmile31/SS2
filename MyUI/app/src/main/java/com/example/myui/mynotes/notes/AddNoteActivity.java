package com.example.myui.mynotes.notes;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myui.R;
import com.example.myui.mynotes.notes.db.NoteManager;
import com.example.myui.mynotes.notes.models.Note;

public class AddNoteActivity extends AppCompatActivity {
    private EditText newNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        newNote = findViewById(R.id.newNote);
        NoteManager noteManager = NoteManager.getInstance(this);

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note(newNote.getText().toString());
                noteManager.add(note);
                setResult(RESULT_OK);
                finish();
            }
        });
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}