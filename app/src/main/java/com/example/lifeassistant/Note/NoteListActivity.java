package com.example.lifeassistant.Note;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.room.Room;

import com.example.lifeassistant.BaseDrawerActivity;
import com.example.lifeassistant.Database.NoteDatabase.NoteDatabase;
import com.example.lifeassistant.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

public class NoteListActivity extends BaseDrawerActivity {
    private FloatingActionButton addNoteButton;
    private RecyclerView recyclerViewNotesList;
    private NoteRecAdapter adapter;
    private List<Note> notes;
    private NoteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.notes_main, contentFrameLayout);
        recyclerViewNotesList = (RecyclerView) findViewById(R.id.mainRecyclerView);
        addNoteButton = (FloatingActionButton) findViewById(R.id.addNoteButton);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OpenedNoteActivity.class);
                intent.putExtra("passedNote",new Note("",""));
                intent.putExtra("noteIndex", 0);
                ((Activity)view.getContext()).startActivityForResult(intent, 1); // new note -> 1
            }
        });
        initializeNotesList();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Note returned = (Note) data.getSerializableExtra("passedNote");
        int noteIndex = (int) data.getIntExtra("noteIndex",0);
        if(resultCode == RESULT_OK) { // everything went fine, the Note is about to exist
            switch(requestCode) {
                case 1: // new note
                    notes.add(0,returned);
                    new SaveNote().execute(returned);
                    adapter.notifyItemInserted(0);
                    break;
                case 2: // changing existing one
                    notes.set(noteIndex, returned);
                    new UpdateNote().execute(returned);
                    adapter.notifyItemChanged(noteIndex);
                    break;
            }
        }
        else if(resultCode == RESULT_FIRST_USER) { // signal to delete the note
            switch(requestCode) {
                case 1: // new note
                    break;
                case 2: // deleting existing one
                    notes.remove(noteIndex);
                    new DeleteNote().execute(returned);
                    adapter.notifyItemRemoved(noteIndex);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initializeNotesList() {
        database = Room.databaseBuilder(this, NoteDatabase.class,"notes").allowMainThreadQueries().build();
        notes = new LinkedList<>(database.noteDao().getAll()); // getting data from database
        adapter = new NoteRecAdapter(notes, recyclerViewNotesList);
        recyclerViewNotesList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerViewNotesList.setAdapter(adapter);
    }
    class SaveNote extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            for (int i = 0; i < notes.length; i++) {
                database.noteDao().insert(notes[i]);
            }
            return null;
        }
    }
    class UpdateNote extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            for (int i = 0; i < notes.length; i++) {
                database.noteDao().update(notes[i]);
            }
            return null;
        }
    }
    class DeleteNote extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            for (int i = 0; i < notes.length; i++) {
                database.noteDao().delete(notes[i]);
            }
            return null;
        }
    }
    class GetNotes extends AsyncTask<Note, Void, List<Note> > {
        @Override
        protected List<Note> doInBackground(Note... notes) {
            List<Note> list = new LinkedList<Note>();
            for (int i = 0; i < notes.length; i++) {
                list.addAll(database.noteDao().getAll());
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            super.onPostExecute(notes);
            adapter.notifyDataSetChanged();
        }
    }

}
