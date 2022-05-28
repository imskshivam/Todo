package com.example.notes;



import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_Database")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority DESC")
    LiveData<List<Notes>> hightolow();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority ASC")
    LiveData<List<Notes>> lowtohigh();

    @Insert
    public void insert(Notes...notes);

    @Query("DELETE FROM Notes_Database WHERE id=:id")
    public void deleteNotes(int id);

    @Update
    public void UpdateNotes(Notes notes);

}
