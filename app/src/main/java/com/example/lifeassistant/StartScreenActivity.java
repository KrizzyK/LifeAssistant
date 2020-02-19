package com.example.lifeassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartScreenActivity extends AppCompatActivity {
    private Button goToNotesActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen_layout);
        goToNotesActivity = (Button) findViewById(R.id.startToNotesButton);
        goToNotesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),NoteListActivity.class));
            }
        });
    }

}
