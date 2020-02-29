package com.example.lifeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.lifeassistant.Counter.CounterActivity;
import com.example.lifeassistant.Dictionary.DictionaryActivity;
import com.example.lifeassistant.Note.NoteListActivity;
import com.example.lifeassistant.ToDoStack.ToDoStackActivity;

public class StartScreenActivity extends BaseDrawerActivity {
    Button goToNotesButton, goToCountersButton, goToDicionaryButton, goToToDoStackButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.start_screen_layout, contentFrameLayout);
        goToNotesButton = (Button) findViewById(R.id.startToNotesButton);
        goToNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NoteListActivity.class));
            }
        });
        goToCountersButton = (Button) findViewById(R.id.startToCountersButton);
        goToCountersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CounterActivity.class));
            }
        });
        goToDicionaryButton = (Button) findViewById(R.id.startToDicionaryButton);
        goToDicionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DictionaryActivity.class));
            }
        });
        goToToDoStackButton = (Button) findViewById(R.id.startToStackButton);
        goToToDoStackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ToDoStackActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
