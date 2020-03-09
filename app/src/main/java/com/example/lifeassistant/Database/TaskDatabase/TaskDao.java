package com.example.lifeassistant.Database.TaskDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lifeassistant.ToDoStack.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM Task ORDER BY createdDate DESC")
    List<Task> getAll();

    @Query("SELECT * FROM Task WHERE createdDate LIKE :xcreatedDate ")
    Task selectById(String xcreatedDate); // id = createddate

    @Delete
    public void delete(Task ... tasks);

    @Update
    public void update(Task ... tasks);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Task ... tasks);
}
