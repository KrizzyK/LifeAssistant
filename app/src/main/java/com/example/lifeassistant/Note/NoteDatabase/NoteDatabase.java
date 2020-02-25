package com.example.lifeassistant.Note.NoteDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lifeassistant.Note.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
