package com.example.lifeassistant.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    private String title;
    private String content;
    private Date modifiedDate;
    private Date createdDate;


    public Note() {}

    public Note(String title, String content, Date modifiedDate, Date createdDate) {
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
    }

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

    public String getModifiedDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(modifiedDate);
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
