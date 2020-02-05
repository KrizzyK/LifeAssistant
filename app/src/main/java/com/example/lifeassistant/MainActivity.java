/* important links
https://stackoverflow.com/questions/31367599/how-to-update-recyclerview-adapter-data
https://stackoverflow.com/questions/21974361/which-java-collection-should-i-use
https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android -> Bundle method (y)

*/
package com.example.lifeassistant;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.lifeassistant.Note.Note;
import com.example.lifeassistant.Note.NoteHolder;
import com.example.lifeassistant.Note.OpenedNoteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton addNoteButton;
    private RecyclerView recyclerViewNotesList;
    private RecyclerViewAdapter adapter;
    private NoteHolder notes;
    private void initializeNotesList() {
        notes = new NoteHolder(new LinkedList<Note>());
        adapter = new RecyclerViewAdapter(notes.getList(), recyclerViewNotesList);
        notes.addSomeRandomStuff();
        recyclerViewNotesList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerViewNotesList.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);
        recyclerViewNotesList = (RecyclerView) findViewById(R.id.mainRecyclerView);
        addNoteButton = (FloatingActionButton) findViewById(R.id.addNoteButton);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note newItem = new Note();
                notes.getList().add(0, newItem);
                Intent intent = new Intent(view.getContext(), OpenedNoteActivity.class);
                intent.putExtra("passedNote",newItem);
                intent.putExtra("noteIndex", 0);
                ((Activity)view.getContext()).startActivityForResult(intent, 1);
            }
        });
        initializeNotesList();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Note returned = (Note) data.getSerializableExtra("passedNote");
            int noteIndex = (int) data.getIntExtra("noteIndex",0);
            notes.setNote(noteIndex, returned);
            adapter.notifyDataSetChanged();
        }


    }

}
