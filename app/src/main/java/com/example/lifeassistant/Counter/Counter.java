package com.example.lifeassistant.Counter;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.lifeassistant.Database.DateConverter;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Counter implements Serializable {

    @ColumnInfo(name = "count")
    private int count;

    @ColumnInfo(name = "name")
    private String name;

    @TypeConverters(DateConverter.class)
    @PrimaryKey
    private Date createdDate;

    @Ignore
    Counter(String name, int count){
        this.name = name;
        this.count = count;
        createdDate = new Date();
    }

    public Counter(int count, String name, Date createdDate) {
        this.count = count;
        this.name = name;
        this.createdDate = createdDate;
    }

    public void increment() {
        count++;
    }
    public void decrement() { count--; }
    public void setToZero() { count = 0; }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDateToString() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(createdDate);
    }

}
