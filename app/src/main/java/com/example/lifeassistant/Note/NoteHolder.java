package com.example.lifeassistant.Note;

import java.util.ArrayList;

public class NoteHolder {
    private ArrayList<Note> list;

    public NoteHolder(ArrayList<Note> list) {
        this.list = list;
    }
    public void addNote(Note note) {
        list.add(note);
    }
    public void removeNote(Note note) {
        list.remove(note);
    }
    public void removeNote(int index) {
        list.remove(index);
    }
    public ArrayList<Note> getList() {
        return list;
    }
    public void setList(ArrayList<Note> list) {
        this.list = list;
    }
}
