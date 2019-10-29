package com.example.lifeassistant;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.lifeassistant.Note.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotesList;
    private List<Note> notesData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewNotesList = (RecyclerView) findViewById(R.id.mainRecyclerView);
        recyclerViewNotesList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        {
            List<Note> notes = new ArrayList<Note>();
            addSomeStuff(notes);
            recyclerViewNotesList.setAdapter(new RecyclerViewAdapter(notes, recyclerViewNotesList));

        }
    }
    private void addSomeStuff(List<Note> list) {
        list.add(new Note("Siema", "Nowa notka"));
        list.add(new Note("Siema2", "Nowa notka"));
        list.add(new Note("Siema3", "Nowa notkajeimhfsoeh sefuihefuisfeui ci besiefih sreuifoemfeuicmehsuicfesomhcsefuicsefuiefhiufephefuimhcuiehmfceuihmceuichmeues"));
        list.add(new Note("Siema4", "Nowa notka"));
        list.add(new Note("Siema5", "Nowa notka"));
        list.add(new Note("Siema6", "Nowa notka"));
        list.add(new Note("Siema7", "Nowa notka"));
        list.add(new Note("Siema8nefismenimpfsiempjuifmjiespcjmeisocjfmiesjoie,jciescjsf,ieospcse,sopjsefcsece", "Nowa notka"));
        list.add(new Note("Siema9", "Nowa notka"));
        list.add(new Note("Siema10", "Nowa notka"));
        list.add(new Note("Siema11iaowp,jeaioj,ioe,jioefj,eioefj,iofej,esio,fsijpjsiofj,so", "Nowa notkaiadpo,wjwaioj,dwaioj,dsioaj,aods,ao"));
        list.add(new Note("Siema12", "Nowa notka"));

    }
}
