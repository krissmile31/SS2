package com.example.myui.mynotes.notes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.myui.mynotes.notes.models.Note;

import java.util.List;

public class NoteManager {
    public static NoteManager instance;
    private static final String INSERT_STMT =
            "INSERT INTO " + DbSchema.NotesTable.NAME + "(text) VALUES (?)";
    private static final String UPDATE_STMT =
            "UPDATE " + DbSchema.NotesTable.NAME + " SET " + DbSchema.NotesTable.Cols.TEXT + "= ? WHERE id = ?";

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public static NoteManager getInstance(Context context) {
        if (instance == null)
            instance = new NoteManager(context);
        return instance;
    }

    private NoteManager(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Note> all() {
        String sql = "SELECT * FROM " + DbSchema.NotesTable.NAME;
        Cursor cursor = db.rawQuery(sql, null);
        NoteCursorWrapper cursorWrapper = new NoteCursorWrapper(cursor);

        return cursorWrapper.getNotes();
    }

    public boolean add(Note note) {
        SQLiteStatement statement = db.compileStatement(INSERT_STMT);

        statement.bindString(1, note.getText());

        long id = statement.executeInsert();
        if (id > 0) {
            note.setId(id);
            return true;
        }
        return false;
    }


    public boolean update(Note note) {
        ContentValues cv = new ContentValues();
        cv.put(DbSchema.NotesTable.Cols.TEXT, note.getText());
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbSchema.NotesTable.NAME +  " WHERE id = ?", new String[]{note.getId() + ""});
        if (cursor.getCount() > 0) {
            long result = db.update(DbSchema.NotesTable.NAME, cv, "id=?", new String[]{note.getId() + ""});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean delete(long id) {
        int result = db.delete(DbSchema.NotesTable.NAME, "id = ?", new String[]{id + ""});
        return result > 0;
    }

    public void clear() {
        db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + DbSchema.NotesTable.NAME);
    }

}
