package com.example.notes.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.notes.Notes;
import com.example.notes.repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository notesRepository;

    public LiveData<List<Notes>> getAllNotes;

    public LiveData<List<Notes>> hightolow;

    public LiveData<List<Notes>> lowtohigh;


    public NotesViewModel(Application application){
        super(application);

        notesRepository=new NotesRepository(application);
        getAllNotes= notesRepository.getAllNotes;
        lowtohigh=notesRepository.lowtohigh;
        hightolow=notesRepository.hightolow;
    }

    public void insert(Notes notes){
        notesRepository.insertNote(notes);
    }

    public void update(Notes notes){
        notesRepository.updateNotes(notes);
    }

    public void delete(int id){
        notesRepository.deleteNotes(id);
    }
}
