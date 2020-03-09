package com.example.lifeassistant.Database.TaskDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.lifeassistant.Database.LocalDateTimeConverter;
import com.example.lifeassistant.ToDoStack.Task;

@Database(entities = {Task.class}, version = 1)
@TypeConverters({LocalDateTimeConverter.class})
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
