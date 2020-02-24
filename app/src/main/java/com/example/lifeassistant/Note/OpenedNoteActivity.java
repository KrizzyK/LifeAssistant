package com.example.lifeassistant.Note;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.lifeassistant.BaseDrawerActivity;
import com.example.lifeassistant.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class OpenedNoteActivity extends BaseDrawerActivity {
    private Note opened_note;
    private int changingNoteIndex;
    private ImageButton back_button, deleteButton;
    private TextInputEditText titlex, contentx;
    private TextView modified, created;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.opened_note_layout, contentFrameLayout);
        back_button = (ImageButton) findViewById(R.id.backButton);
        deleteButton = (ImageButton) findViewById(R.id.deleteButton);
        titlex = (TextInputEditText) findViewById(R.id.noteTitleHint);
        contentx = (TextInputEditText) findViewById(R.id.noteContentHint);
        modified = (TextView) findViewById(R.id.noteModifiedDateOpened);
        created = (TextView) findViewById(R.id.noteCreatedDateOpened);
        initializeNote();
        initializeBackButton();
        initializeDeleteButton();
    }
    private void initializeNote() {
        opened_note = (Note) getIntent().getSerializableExtra("passedNote");
        changingNoteIndex = getIntent().getIntExtra("noteIndex", 0);
        titlex.setText(opened_note.getTitle());
        contentx.setText(opened_note.getContent());
        modified.setText(opened_note.getModifiedDateToString());
        created.setText(opened_note.getCreatedDateToString());
    }
    private void initializeBackButton() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // zakonczenie tego ekranu
                backToNoteList();
            }
        });
    }
    private void backToNoteList() {
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
    private void initializeDeleteButton() {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // should open a dialog with cancel and Delete
                deleteDialog(view);
            }
        });
    }
    private void deleteDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.ThemeDialogCustom);
        builder.setTitle("Are you sure you want to delete the note?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setResult(RESULT_FIRST_USER, getIntent());
                finish();
                dialogInterface.dismiss();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            backToNoteList();
        }

    }
}
