package com.example.myui.mynotes.notes.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.myui.mynotes.notes.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote(){
        long id = getLong(getColumnIndex("id"));
        String text = getString(getColumnIndex(DbSchema.NotesTable.Cols.TEXT));

        Note note = new Note(id, text);

        return note;

    }

    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();

        moveToFirst();
        while (!isAfterLast()) {
            Note note = getNote();
            notes.add(note);

            moveToNext();
        }

        return notes;
    }
}
