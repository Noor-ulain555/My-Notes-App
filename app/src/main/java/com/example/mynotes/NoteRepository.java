package com.example.mynotes;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NoteRepository {
        private notedao noteDao;
        private LiveData<List<note>> noteList;
        private Executor executor = Executors.newSingleThreadExecutor();

        public NoteRepository(Application application) {
            noteDB noteDatabase = noteDB.getInstance(application);
            noteDao = noteDatabase.noteDao();
            noteList = noteDao.getAllData();
        }

        public LiveData<List<note>> getAllData() {
            return noteList;
        }

        public void insertData(note note) {
            executor.execute(() -> noteDao.insert(note));
        }

        public void deleteData(note note) {
            executor.execute(() -> noteDao.delete(note));
        }

        public void updateData(note note) {
            executor.execute(() -> noteDao.update(note));
        }
    }