package com.example.notes.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notes.NoteDatabase;
import com.example.notes.Notes;
import com.example.notes.NotesDao;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getAllNotes;

    public LiveData<List<Notes>> hightolow;

    public LiveData<List<Notes>> lowtohigh;



    public NotesRepository(Application application){
        NoteDatabase noteDatabase=NoteDatabase.getDatabaseInstance(application);
        notesDao= noteDatabase.notesDao();
        getAllNotes=notesDao.getAllNotes();
        hightolow=notesDao.hightolow();
        lowtohigh=notesDao.lowtohigh();
    }

   public void insertNote(Notes notes){
        notesDao.insert(notes);
    }

  public   void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }

   public void updateNotes(Notes notes){
        notesDao.UpdateNotes(notes);
    }
}
