package com.example.lifeassistant.Note;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.lifeassistant.Note.RoomDatabase.DateConverter;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Note implements Serializable {
    //@PrimaryKey//(autoGenerate = true)
    //public int noteIndex;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "content")
    private String content;
    @TypeConverters(DateConverter.class)
    @ColumnInfo(name = "modifiedDate")
    private Date modifiedDate;
    @TypeConverters(DateConverter.class)
    //@ColumnInfo(name = "createdDate")
    @PrimaryKey
    private Date createdDate;

    @Ignore
    public Note() {
    }




    public Note(String title, String content, Date modifiedDate, Date createdDate) {
        //this.noteIndex = noteIndex;
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
    }
    @Ignore
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        createdDate = new Date();
        modifiedDate = new Date();
    }
    public Note getClusteredNote() { // do dopracowania?
        String titlex, contentx;
        titlex = title.substring(0, (title.length() < 35 ? title.length() : 35));
        int contentxlen = 200 - titlex.length();
        contentx = content.substring(0, content.length() < contentxlen ? content.length() : contentxlen);
        return new Note(titlex, contentx, modifiedDate, createdDate);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public String getModifiedDateToString() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(modifiedDate);
    }
    public String getCreatedDateToString() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return df.format(createdDate);
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
