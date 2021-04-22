package com.example.myui.mynotes.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.myui.R;
import com.example.myui.mynotes.notes.adapters.NoteAdapter;
import com.example.myui.mynotes.notes.db.NoteManager;
import com.example.myui.mynotes.notes.models.Note;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
    public static final int  NOTE_ADD = 1;
    public static final int  NOTE_EDIT = 2;
    private RecyclerView recyclerView;
    private List<Note> notes;
    private NoteAdapter noteAdapter;
    private NoteManager noteManager;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        recyclerView = findViewById(R.id.recycleView);

        noteManager = NoteManager.getInstance(this);
        notes = noteManager.all();

        noteAdapter = new NoteAdapter(notes);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noteAdapter.setOnEditClickListener(new NoteAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(Note note) {
                position = notes.indexOf(note);
                Intent intent = new Intent(NoteActivity.this, EditNoteActivity.class);
                intent.putExtra(EditNoteActivity.EXTRA_TEXT, note.getText());
                startActivityForResult(intent, NOTE_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == NOTE_ADD) {
            notes.clear();
            notes.addAll(noteManager.all());
            noteAdapter.notifyDataSetChanged();
        }
        else if (resultCode == RESULT_OK && requestCode == NOTE_EDIT) {
//            Note note = (Note) data.getSerializableExtra("EDIT_NOTE");
//            notes.set(position, note);
            notes.clear();
            notes.addAll(noteManager.all());
            noteAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(NoteActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, NOTE_ADD);
                break;

            case R.id.clear:
                new AlertDialog.Builder(NoteActivity.this)
                        .setIcon(R.drawable.unchecked)
                        .setTitle("Are you sure to delete all notes?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.clear();
                                noteManager.clear();
                                Intent intent = new Intent(NoteActivity.this, NoteActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();
        }
        return super.onOptionsItemSelected(item);
    }
}