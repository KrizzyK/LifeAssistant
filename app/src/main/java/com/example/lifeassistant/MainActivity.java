package com.example.lifeassistant;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.lifeassistant.Note.Note;
import com.example.lifeassistant.Note.NoteHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotesList;
    private RecyclerViewAdapter adapter;
//    private List<Note> notesData = new ArrayList<>();
    private NoteHolder notes;
    private void initializeNotesList() {
        notes = new NoteHolder(new ArrayList<Note>());
        adapter = new RecyclerViewAdapter(notes.getList(), recyclerViewNotesList);


        recyclerViewNotesList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        {
            notes.addSomeRandomStuff();
            recyclerViewNotesList.setAdapter(adapter);
        }
        //adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);
        recyclerViewNotesList = (RecyclerView) findViewById(R.id.mainRecyclerView);
        initializeNotesList();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Log.i("MainActivty:","DATA= "+ data.getIntExtra("noteIndex", 1111));
        Note returned = (Note) data.getSerializableExtra("passedNote");
        int noteIndex = (int) data.getIntExtra("noteIndex",0);
        notes.setNote(noteIndex, returned);
        adapter.notifyDataSetChanged();

    }

}
