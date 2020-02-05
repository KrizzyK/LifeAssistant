package com.example.lifeassistant;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lifeassistant.Note.Note;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class OpenedNoteActivity extends AppCompatActivity {
    private Note opened_note;
    private int changingNoteIndex;
    private ImageButton back_button, deleteButton;
    private TextInputEditText titlex, contentx;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opened_note_layout);
        back_button = (ImageButton) findViewById(R.id.backButton);
        deleteButton = (ImageButton) findViewById(R.id.deleteButton);
        titlex = (TextInputEditText) findViewById(R.id.noteTitleHint);
        contentx = (TextInputEditText) findViewById(R.id.noteContentHint);
        initializeNote();
        initializeBackButton();
        initializeDeleteButton();
    }
    private void initializeNote() {
        opened_note = (Note) getIntent().getSerializableExtra("passedNote");
        changingNoteIndex = getIntent().getIntExtra("noteIndex", 0);
        titlex.setText(opened_note.getTitle());
        contentx.setText(opened_note.getContent());
    }
    private void initializeBackButton() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // zakonczenie tego ekranu
                Intent intent = new Intent();
                intent.putExtra("noteIndex", changingNoteIndex);
                opened_note.setTitle( titlex.getText().toString() );
                opened_note.setContent( contentx.getText().toString() );
                opened_note.setModifiedDate( new Date() );
                if(!opened_note.getTitle().isEmpty() || !opened_note.getContent().isEmpty()) {
                    intent.putExtra("passedNote", opened_note);
                    setResult(RESULT_OK, intent);
                } else setResult(RESULT_FIRST_USER, intent); // empty note, gonna delete that one
                finish();
            }
        });
    }
    private void initializeDeleteButton() {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // should open a dialog with cancel and Delete
                deleteDialog(view);
            }
        });
    }
    private void deleteDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Delete this?");
        builder.setMessage("Are you sure you want to delete this note?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setResult(RESULT_FIRST_USER, getIntent());
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
