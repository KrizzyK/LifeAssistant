package com.example.lifeassistant.ToDoStack;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.lifeassistant.BaseDrawerActivity;
import com.example.lifeassistant.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

import java.util.Calendar;
import java.util.Objects;

public class OpenedTaskActivity extends BaseDrawerActivity {
    private Task openedTask;
    private Task startingTask;
    private int changingTaskIndex;
    Button timePicker, datePicker;
    ImageButton backButton, deleteButton;
    EditText taskName;
    TextView deadlineDate, deadlineTime;
    LocalTime pickedTime;
    LocalDate pickedDate;
    Spinner prioritySpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.opened_task_layout, contentFrameLayout);
        taskName = (EditText) findViewById(R.id.taskNamev2);
        deadlineDate = (TextView) findViewById(R.id.deadlineDate);
        deadlineTime = (TextView) findViewById(R.id.deadlineTime);
        timePicker = (Button) findViewById(R.id.timePicker);
        datePicker = (Button) findViewById(R.id.datePicker);
        backButton = (ImageButton) findViewById(R.id.backToStackButton);
        deleteButton = (ImageButton) findViewById(R.id.deleteTaskButton2);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickTime();
            }
        });
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDate();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToToDoStack();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDeleteDialog(view);
            }
        });

        openedTask = (Task) getIntent().getSerializableExtra("passedTask");
        startingTask = openedTask;
        changingTaskIndex = getIntent().getIntExtra("taskIndex", 0);
        taskName.setText(openedTask.getTaskInfo());
        pickedTime = openedTask.getDeadlineTimeOnly();
        pickedDate = openedTask.getDeadlineDateOnly();
        deadlineDate.setText("Deadline date: " + pickedDate.toString());
        deadlineTime.setText("Deadline time: " + pickedTime.toString());
        taskName.setText(openedTask.getTaskInfo());
        initializeSpinner();

    }

    private void pickDate() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1; // it gives me value from 0 to 11, i need 1-12
                String zero = (month<10 ? "0" : "");
                pickedDate = LocalDate.of(year, month, day);
                deadlineDate.setText("Deadline date: " + day + "." + zero + month + "." + year);
                openedTask.setDeadlineDate(LocalDateTime.of( pickedDate, pickedTime) );
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(OpenedTaskActivity.this,
                android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                onDateSetListener, mYear, mMonth, mDay);
        datePickerDialog.setTitle("Set Date");
        Objects.requireNonNull(datePickerDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        datePickerDialog.show();
    }
    private void pickTime() {
        final Calendar calenderInstance = Calendar.getInstance();
        int hr = calenderInstance.get(Calendar.HOUR_OF_DAY);
        int min = calenderInstance.get(Calendar.MINUTE);
        TimePickerDialog.OnTimeSetListener onTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    String zero = (minute<10 ? "0" : "");
                    pickedTime = LocalTime.of(hourOfDay, minute);
                    deadlineTime.setText("Deadline time: " + hourOfDay+ ":" + zero + minute);
                    openedTask.setDeadlineDate(LocalDateTime.of( pickedDate, pickedTime) );
                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(OpenedTaskActivity.this,
                android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                onTimeListener, hr, min, true);
        timePickerDialog.setTitle("Set Time");
        Objects.requireNonNull(timePickerDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }


    private void backToToDoStack() {
        LocalDateTime deadline;
        Intent intent = new Intent();
        intent.putExtra("taskIndex", changingTaskIndex);
        if(pickedTime == null || pickedDate == null || taskName.getText().toString().isEmpty() ) {
            Toast.makeText(this, "You haven't provided enough data.", Toast.LENGTH_SHORT).show();
            setResult(RESULT_FIRST_USER, intent);
        } else {
            deadline = LocalDateTime.of(pickedDate, pickedTime);
            LocalDateTime now = LocalDateTime.now();
            if( now.isAfter(deadline) ) {
                Toast.makeText(this, "Wrong date or time.", Toast.LENGTH_SHORT).show();
                setResult(RESULT_FIRST_USER, intent);
                intent.putExtra("passedTask", startingTask);
            } else {
                openedTask.setTaskInfo(taskName.getText().toString());
                setResult(RESULT_OK, intent);
                intent.putExtra("passedTask", openedTask);
            }
        }
        finish();
    }
    private void makeDeleteDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.ThemeDialogCustom);
        builder.setTitle("Are you sure you want to delete the task?");
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
    private void initializeSpinner() {
        prioritySpinner = findViewById(R.id.prioritySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priorities_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(adapter);
        prioritySpinner.setSelection( openedTask.getTaskPriority().getValue() );
        prioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String elo = adapterView.getItemAtPosition(i).toString();
                switch (elo) {
                    case "LOW":
                        openedTask.setTaskPriority(Priority.LOW);
                        break;
                    case "MEDIUM":
                        openedTask.setTaskPriority(Priority.MEDIUM);
                        break;
                    case "HIGH":
                        openedTask.setTaskPriority(Priority.HIGH);
                        break;
                    case "URGENT":
                        openedTask.setTaskPriority(Priority.URGENT);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        backToToDoStack();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            backToToDoStack();
        }

    }

}
