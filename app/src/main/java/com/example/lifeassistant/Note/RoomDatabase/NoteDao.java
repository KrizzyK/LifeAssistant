package com.example.lifeassistant.Note.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lifeassistant.Note.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM Note ORDER BY createdDate DESC")
    List<Note> getAll();
    @Query("SELECT * FROM Note WHERE createdDate LIKE :xcreatedDate ")
    Note selectById(String xcreatedDate); // id = createddate
    @Delete
    public void delete(Note note);
    @Update
    public void update(Note ... notes);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Note ... notes);



}
