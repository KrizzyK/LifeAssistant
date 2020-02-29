package com.example.lifeassistant.Database.CounterDatabase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lifeassistant.Counter.Counter;

import java.util.List;

@Dao
public interface CounterDao {

    @Query("SELECT * FROM Counter ORDER BY createdDate DESC")
    List<Counter> getAll();

    @Query("SELECT * FROM Counter WHERE createdDate LIKE :xcreatedDate")
    Counter selectById(String xcreatedDate);

    @Delete
    public void delete (Counter ... counter);

    @Update
    public void update(Counter ... counters);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Counter ... counters);
}
