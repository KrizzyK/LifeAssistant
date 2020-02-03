package com.example.lifeassistant;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.lifeassistant.Note.Note;
import com.example.lifeassistant.Note.NoteHolder;

import java.util.ArrayList;
///////////////////////////////////////////////// aby kafelki mialy odpowiedni rozmiar nalezy zrobic druga liste, ktora ma powiedzmy 100 liter
///////////////////////////////////////////////// a potem to wsadzic jako liste w kazdy kafelek. wtedy wrap content bedzie dzialal poprawnie
///////////////////////////////////////////////// dodatkowo dowiedz sie jak relatywnie rozmieszczac te obiekty na ekranie.
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotesList;
//    private List<Note> notesData = new ArrayList<>();
    private NoteHolder notes;
    private void initializeNotesList() {
        recyclerViewNotesList = (RecyclerView) findViewById(R.id.mainRecyclerView);
        recyclerViewNotesList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        {
            notes.addSomeRandomStuff();
            recyclerViewNotesList.setAdapter(new RecyclerViewAdapter(notes.getList(), recyclerViewNotesList));
        }
    }
    public MainActivity() {
        notes = new NoteHolder(new ArrayList<Note>());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);
        initializeNotesList();

    }

}
