package com.example.lifeassistant;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Note.Note;
import Note.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView noteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteList = (RecyclerView) findViewById(R.id.noteList);
        noteList.setHasFixedSize(true);
        noteList.setLayoutManager(new LinearLayoutManager(this));
        noteList.setItemAnimator(new DefaultItemAnimator());

        {
            List<Note> notes = new ArrayList<Note>();
            notes.add(new Note("placek","jest rózowy"));
            noteList.setAdapter(new RecyclerViewAdapter(notes, noteList));

        }
    }
}
