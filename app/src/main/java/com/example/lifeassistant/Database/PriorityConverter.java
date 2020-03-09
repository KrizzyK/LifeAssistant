package com.example.lifeassistant.Database;

import androidx.room.TypeConverter;

import com.example.lifeassistant.ToDoStack.Priority;

public class PriorityConverter {

    @TypeConverter
    public Priority toValue(int value) {
        return Priority.valueOf(value);
    }

    @TypeConverter
    public int fromPriority(Priority pri) {
        return pri.getValue();
    }
}
