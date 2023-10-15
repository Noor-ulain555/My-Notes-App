package com.example.mynotes;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepo;
    private LiveData<List<note>> noteList;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepo = new NoteRepository(application);
        noteList=noteRepo.getAllData();
    }

    public void insert(note note) {
        noteRepo.insertData(note);
    }

    public void delete(note note) {
        noteRepo.deleteData(note);
    }

    public void update(note note) {
        noteRepo.updateData(note);
    }

    public LiveData<List<note>> getNoteList() {
        return noteList;
    }
}