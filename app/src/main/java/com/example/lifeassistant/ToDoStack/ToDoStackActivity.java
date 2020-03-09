package com.example.lifeassistant.ToDoStack;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.lifeassistant.BaseDrawerActivity;
import com.example.lifeassistant.Database.TaskDatabase.TaskDatabase;
import com.example.lifeassistant.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.LinkedList;
import java.util.List;

public class ToDoStackActivity extends BaseDrawerActivity {
    RecyclerView taskRecView;
    FloatingActionButton addTaskButton;
    private TaskAdapter adapter;
    private List<Task> toDoList;
    private List<Task> doneTasksList;
    private TaskDatabase taskDatabase;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Task returned = (Task) data.getSerializableExtra("passedTask");
        int index = (int) data.getIntExtra("taskIndex",0);

        if(resultCode == RESULT_OK) { // everything went fine, the task is about to exist
            switch(requestCode) {
                case 1: // new task
                    toDoList.add(0,returned);
                    new SaveTask().execute(returned);
                    adapter.notifyItemInserted(0);
                    break;
                case 2: // changing existing one
                    toDoList.set(index, returned);
                    new UpdateTask().execute(returned);
                    adapter.notifyItemChanged(index);
                    break;
            }
        }
        else if(resultCode == RESULT_FIRST_USER) { // signal to delete the task
            switch(requestCode) {
                case 1: // new task
                    break;
                case 2: // deleting existing one
                    new DeleteTask().execute(returned);
                    toDoList.remove(index);
                    adapter.notifyItemRemoved(index);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this); //  "Initialize the timezone information in your Application.onCreate() method:"
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.todostack_main, contentFrameLayout);
        taskDatabase = Room.databaseBuilder(this, TaskDatabase.class,"tasks").allowMainThreadQueries().build();
        toDoList = new LinkedList<>(taskDatabase.taskDao().getAll()); // getting data from database

        taskRecView = (RecyclerView) findViewById(R.id.tasksRecView);
        adapter = new TaskAdapter(toDoList, doneTasksList, taskRecView, taskDatabase);
        taskRecView.setLayoutManager(new LinearLayoutManager(this));
        taskRecView.setAdapter(adapter);
        addTaskButton = (FloatingActionButton) findViewById(R.id.addTaskButton);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // new task
                Intent intent = new Intent(view.getContext(), OpenedTaskActivity.class);
                intent.putExtra("passedTask", new Task());
                intent.putExtra("index", 0);
                startActivityForResult(intent, 1); // 1 => adding new task
            }
        });



    }


    class SaveTask extends AsyncTask<Task, Void, Void> {
        @Override
        protected Void doInBackground(Task... tasks) {
            taskDatabase.taskDao().insert(tasks);
            return null;
        }
    }
    class UpdateTask extends AsyncTask< Task, Void, Void> {
        @Override
        protected Void doInBackground(Task... list) {
            taskDatabase.taskDao().update(list);
            return null;
        }
    }
    class DeleteTask extends AsyncTask<Task, Void, Void> {
        @Override
        protected Void doInBackground(Task... tasks) {
            taskDatabase.taskDao().delete(tasks);
            return null;
        }
    }

}
