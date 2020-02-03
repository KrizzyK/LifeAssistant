package com.example.lifeassistant.Note;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lifeassistant.R;
import com.google.android.material.textfield.TextInputEditText;

public class OpenedNoteActivity extends AppCompatActivity {
    private Note opened_note;
    private ImageButton back_button;
    private TextInputEditText titlex, contentx;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opened_note_layout);
        back_button = (ImageButton) findViewById(R.id.backButton);
        titlex = (TextInputEditText) findViewById(R.id.noteTitleHint);
        contentx = (TextInputEditText) findViewById(R.id.noteContentHint);
        opened_note = (Note) getIntent().getSerializableExtra("passedNote");
        //titlex.setText("Siema1");
        //contentx.setText(opened_note.getContent());
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
