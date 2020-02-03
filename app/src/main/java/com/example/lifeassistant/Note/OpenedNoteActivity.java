package com.example.lifeassistant.Note;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifeassistant.R;

public class OpenedNoteActivity extends AppCompatActivity {
    private Note opened_note;
    private ImageButton back_button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opened_note_layout);
        back_button = (ImageButton) findViewById(R.id.backButton);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
