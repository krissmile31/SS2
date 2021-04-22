package com.example.myui.mynotes.notes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DbSchema.NotesTable.NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbSchema.NotesTable.Cols.TEXT + " TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("My Notes", "My Notes: upgrading DB; dropping/recreating tables.");
        db.execSQL("DROP TABLE IF EXISTS " + DbSchema.NotesTable.NAME);
        onCreate(db);
    }
}
