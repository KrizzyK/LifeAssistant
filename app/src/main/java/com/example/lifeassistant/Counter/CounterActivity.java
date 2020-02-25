package com.example.lifeassistant.Counter;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.lifeassistant.BaseDrawerActivity;
import com.example.lifeassistant.Counter.CounterDatabase.CounterDatabase;
import com.example.lifeassistant.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

public class CounterActivity extends BaseDrawerActivity {
    FloatingActionButton addCounterButton;
    private RecyclerView counterRecyclerView;
    CounterRecAdapter counterRecAdapter;
    List<Counter> counterList;
    private CounterDatabase database;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.counters_main, contentFrameLayout);

        database = Room.databaseBuilder(this, CounterDatabase.class,"counters").allowMainThreadQueries().build();
        counterList = new LinkedList<>(database.counterDao().getAll()); // getting data from database

        counterRecyclerView = findViewById(R.id.counterRecView);

        counterRecAdapter = new CounterRecAdapter(counterList, counterRecyclerView, database);
        counterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        counterRecyclerView.setAdapter(counterRecAdapter);

        addCounterButton = (FloatingActionButton) findViewById(R.id.addCounterButtton);
        addCounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeAddCounterDialog(view);
            }
        });


    }
    private void makeAddCounterDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.ThemeDialogCustom);
        builder.setTitle("Edit the counter:");
        View view1 = (LayoutInflater.from(view.getContext()) ).inflate(R.layout.edit_counter_dialog, null);
        final EditText counterName = (EditText) view1.findViewById(R.id.dialogCounterName);
        final EditText counterCount = (EditText) view1.findViewById(R.id.dialogCount);

        builder.setView(view1)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if( !(counterName.getText().toString().isEmpty()) ) {
                            int count;
                            if( counterCount.getText().toString().isEmpty() ) {
                                count = 0;
                            } else {
                                count = Integer.parseInt( counterCount.getText().toString() );
                            }
                            counterList.add(0, new Counter( counterName.getText().toString() , count ));
                            counterRecAdapter.notifyItemInserted(0);
                            new SaveCounter().execute( counterList.get(0) );
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new UpdateCounter().execute( counterList.toArray(new Counter[0]) );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new UpdateCounter().execute( counterList.toArray(new Counter[0]) );

    }
    class SaveCounter extends AsyncTask<Counter, Void, Void> {
        @Override
        protected Void doInBackground(Counter... counters) {
            database.counterDao().insert(counters);
            return null;
        }
    }


    class UpdateCounter extends AsyncTask< Counter, Void, Void> {
        @Override
        protected Void doInBackground(Counter ... list) {
            database.counterDao().update(list);
            return null;
        }
    }


}
