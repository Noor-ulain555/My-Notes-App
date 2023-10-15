package com.example.mynotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mynotes.note;

import java.util.List;

@Dao
public interface notedao {
    @Insert
    void insert(note note);

    @Delete
    void delete(note note);

    @Update
    void update(note note);

    @Query("SELECT * FROM my_notes")
    LiveData<List<note>> getAllData();
}
