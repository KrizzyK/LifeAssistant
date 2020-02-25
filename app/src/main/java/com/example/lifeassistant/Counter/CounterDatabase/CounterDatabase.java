package com.example.lifeassistant.Counter.CounterDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lifeassistant.Counter.Counter;

@Database(entities = {Counter.class}, version = 1)
public abstract class CounterDatabase extends RoomDatabase {
    public abstract CounterDao counterDao();
}
