package com.example.lifeassistant.ToDoStack;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeassistant.BaseDrawerActivity;
import com.example.lifeassistant.R;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Month;

import java.util.ArrayList;
import java.util.List;

public class ToDoStackActivity extends BaseDrawerActivity {
    RecyclerView taskRecView;
    private TaskAdapter adapter;
    private List<Task> toDoList;
    private List<Task> doneTasksList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this); //  "Initialize the timezone information in your Application.onCreate() method:"
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.todostack_main, contentFrameLayout);
        toDoList = new ArrayList<>(); doneTasksList = new ArrayList<>();
        taskCreation(toDoList);
        taskRecView = (RecyclerView) findViewById(R.id.tasksRecView);
        adapter = new TaskAdapter(toDoList, doneTasksList, taskRecView);
        taskRecView.setLayoutManager(new LinearLayoutManager(this));
        taskRecView.setAdapter(adapter);



    }


    private static void taskCreation(List<Task> tasks) {
        tasks.add(new Task(Priority.LOW, "Low zadanie", LocalDate.of(2020,12,31)));
        tasks.add(new Task(Priority.LOW, "Low zadanie2", LocalDateTime.of(2020,02,28, 00,00) ) );
        tasks.add(new Task(Priority.HIGH, "High zadanie", LocalDate.of(2020,2,20)));
        tasks.add(new Task(Priority.MEDIUM, "Medium zadanie",LocalDate.of(2020,3,1)));
    }

    private int getAmountOfDaysInMonth(Month month) {
        switch (month) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                return 31;
            case FEBRUARY:
                return 29;
            case JUNE:
            case APRIL:
            case NOVEMBER:
            case SEPTEMBER:
                return 30;
        }
        return 0;
    }
}
