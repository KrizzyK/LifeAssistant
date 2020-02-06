package com.example.lifeassistant.Note;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM Note")
    List<Note> getAll();
    @Query("SELECT * FROM Note WHERE noteIndex LIKE :id ")
    Note selectById(int id);
    @Delete
    public void delete(Note note);
    @Update
    public void update(Note ... notes);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Note ... notes);

    //@Insert

}
