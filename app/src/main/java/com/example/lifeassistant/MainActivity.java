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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.lifeassistant.Note.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton addNoteButton;
    private RecyclerView recyclerViewNotesList;
    private RecyclerViewAdapter adapter;
    private List<Note> notes;
    private void initializeNotesList() {
        notes = new LinkedList<>();
        adapter = new RecyclerViewAdapter(notes, recyclerViewNotesList);
        addSomeRandomStuff();
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
                Intent intent = new Intent(view.getContext(), OpenedNoteActivity.class);
                intent.putExtra("passedNote",new Note());
                intent.putExtra("noteIndex", 0);
                ((Activity)view.getContext()).startActivityForResult(intent, 1); // new note -> 1
            }
        });
        initializeNotesList();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //AAAAAAAAAAAAAAAAAAAAAAAAAAA
        super.onActivityResult(requestCode, resultCode, data);
        Note returned = (Note) data.getSerializableExtra("passedNote");
        int noteIndex = (int) data.getIntExtra("noteIndex",0);
        if(resultCode == RESULT_OK) { // everything went fine, the Note is about to exist

            switch(requestCode) {
                case 1: // new note
                    notes.add(0,returned);
                    adapter.notifyItemInserted(0);
                    break;
                case 2: // changing existing one
                    notes.set(noteIndex, returned);
                    adapter.notifyItemChanged(noteIndex);
                    break;
            }
        }
        else if(resultCode == RESULT_FIRST_USER) { // signal to delete the note
            switch(requestCode) {
                case 1: // new note
                    break;
                case 2: // changing existing one
                    notes.remove(noteIndex);
                    adapter.notifyItemRemoved(noteIndex);
                    break;
            }
            Toast.makeText(this, "Deleted note.", Toast.LENGTH_SHORT).show();
        }


    }
    public void addSomeRandomStuff() {
        notes.add(new Note("Siema", "Nowa notka"));
        notes.add(new Note("", ""));
        notes.add(new Note("Siema3", "Nowa notkajeimhfsoeh sefuihefuisfeuigdszgzg ci bvnuisdhisdhfsuifmcsuihdfcsuimchuishmfcsuicmhfuishmcuifsmhcfuishmcfuishmcuihsdfmcuismhcfuishmcfsuihmcfuishmfcuismhcfuismhcfuismhfcuismhcfuismhcfuismhcuismhcfuismhcfsuimchfsuicmhfsiesiefih sreuifoemfeuicmehsuicfesomhcsefuicsefuiefhiufephefuimhcuiehmfceuihmceuichmeues"));
        notes.add(new Note("Siema4", "Nowa notka"));
        notes.add(new Note("Siema5", ""));
        notes.add(new Note("", "Nowa notka"));
        notes.add(new Note("Siema7", "Nowa notka"));
        notes.add(new Note("Siema8nefismenimpfsiempjuifmjiespcjmeisocjfmiesjoie,jciescjsf,ieospcse,sopjsefcsece", "Nowa notka"));
        notes.add(new Note("Siema9", "Nowa notka"));
        notes.add(new Note("Siema10", "Nowa notka"));
        notes.add(new Note("Siema11iaowp,jeaioj,ioe,jioefj,eioefj,iofej,esio,fsijpjsiofj,so", "Nowa notkaiadpo,wjwaioj,dwaioj,dsioaj,aods,ao"));
        notes.add(new Note("Siema12", "Nowa notka"));
    }

}
