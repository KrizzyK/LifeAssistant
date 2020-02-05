package com.example.lifeassistant.Note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifeassistant.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class OpenedNoteActivity extends AppCompatActivity {
    private Note opened_note;
    private int changingNoteIndex;
    private ImageButton back_button;
    private TextInputEditText titlex, contentx;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opened_note_layout);
        initializeNote();
    }
    private void initializeNote() {
        back_button = (ImageButton) findViewById(R.id.backButton);
        titlex = (TextInputEditText) findViewById(R.id.noteTitleHint);
        contentx = (TextInputEditText) findViewById(R.id.noteContentHint);
        opened_note = (Note) getIntent().getSerializableExtra("passedNote");
        changingNoteIndex = getIntent().getIntExtra("noteIndex", 0);
        titlex.setText(opened_note.getTitle());
        contentx.setText(opened_note.getContent());
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // zakonczenie tego ekranu
                Intent intent = getIntent();
                opened_note.setTitle( titlex.getText().toString() );
                opened_note.setContent( contentx.getText().toString() );
                opened_note.setModifiedDate( new Date() );
                intent.putExtra("passedNote", opened_note);
                intent.putExtra("noteIndex", changingNoteIndex);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
