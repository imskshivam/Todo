package com.example.notes;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Notes.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NoteDatabase INSTANCE;

    public static NoteDatabase getDatabaseInstance(Application application){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(application.getApplicationContext(),
                    NoteDatabase.class,
                    "Notes_Database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
