package com.example.mynotes;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mynotes.note;

@Database(entities = {note.class}, version = 4)
public abstract class noteDB extends RoomDatabase {
    private static noteDB instance;

    public abstract notedao noteDao();

    public static synchronized noteDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            noteDB.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
