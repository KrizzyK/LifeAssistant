// https://stackoverflow.com/questions/36986087/recyclerview-adapter-onbindviewholder-gets-wrong-position ????? why bully me

package com.example.lifeassistant.ToDoStack;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeassistant.Database.TaskDatabase.TaskDatabase;
import com.example.lifeassistant.R;

import java.util.List;

class TaskAdapter extends RecyclerView.Adapter {

    private List<Task> taskList;
    private List<Task> donetasksList;
    private RecyclerView taskRecView;
    private TaskDatabase taskDatabase;

    public TaskAdapter(List<Task> taskList, List<Task> donetasksList, RecyclerView taskRecView, TaskDatabase taskDatabase) {
        this.taskList = taskList;
        this.taskRecView = taskRecView;
        this.donetasksList = donetasksList;
        this.taskDatabase = taskDatabase;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton taskDoneButton, editTaskButton, deleteTaskButton;
        TextView deadlineDate, taskName, timeLeft;
        ConstraintLayout taskCardColor;

        public MyViewHolder(View pItem) {
            super(pItem);
            deadlineDate = (TextView) pItem.findViewById(R.id.deadLine);
            taskName = (TextView) pItem.findViewById(R.id.taskName);
            timeLeft = (TextView) pItem.findViewById(R.id.timeLeft);
            taskDoneButton = (ImageButton) pItem.findViewById(R.id.taskDoneButton);
            editTaskButton = (ImageButton) pItem.findViewById(R.id.editTaskButton);
            deleteTaskButton = (ImageButton) pItem.findViewById(R.id.deleteTaskButton);
            taskCardColor = (ConstraintLayout) pItem.findViewById(R.id.taskCardColor);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_task_layout, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // po wcisnieciu na taska
                int itemPosition = taskRecView.getChildLayoutPosition(view);
                Task item = taskList.get(itemPosition);
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        // uzupelniamy informacje o taskach w widoku z listy taskList
        final Task item = taskList.get(position);
        ((MyViewHolder) holder).taskName.setText(item.getTaskInfo());
        ((MyViewHolder) holder).deadlineDate.setText("Deadline: "+ item.deadlineDate.toString());
        ((MyViewHolder) holder).timeLeft.setText("Time left: " + item.getTimeLeft());

        switch( item.getTaskPriority() ) {
            case LOW:
                ((MyViewHolder) holder).taskCardColor.setBackgroundColor
                        ( ContextCompat.getColor(((MyViewHolder) holder).taskCardColor.getContext(), R.color.low_priority_color) ) ;
                break;
            case MEDIUM:
                ((MyViewHolder) holder).taskCardColor.setBackgroundColor
                        ( ContextCompat.getColor(((MyViewHolder) holder).taskCardColor.getContext(), R.color.medium_priority_color) ) ;
                break;
            case HIGH:
                ((MyViewHolder) holder).taskCardColor.setBackgroundColor
                        ( ContextCompat.getColor(((MyViewHolder) holder).taskCardColor.getContext(), R.color.high_priority_color) ) ;
                break;
            case URGENT:
                ((MyViewHolder) holder).taskCardColor.setBackgroundColor
                        ( ContextCompat.getColor(((MyViewHolder) holder).taskCardColor.getContext(), R.color.urgent_priority_color) ) ;
                break;
        }
        if(item.getTaskPriority() == Priority.MEDIUM) {
            ((MyViewHolder) holder).taskCardColor.setBackgroundColor
                    ( ContextCompat.getColor(((MyViewHolder) holder).taskCardColor.getContext(), R.color.medium_priority_color) ) ; // changing color of the task
        }
        ((MyViewHolder) holder).deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDeleteDialog(view, position);
            }
        });
        ((MyViewHolder) holder).editTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OpenedTaskActivity.class);
                intent.putExtra("passedTask", item);
                intent.putExtra("taskIndex", holder.getAdapterPosition());
                ((Activity)view.getContext()).startActivityForResult(intent, 2); // changing existing one
            }
        });
        ((MyViewHolder) holder).taskDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDoneDialog(view,position);
            }
        });

    }

    private void makeDoneDialog(View view, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.ThemeDialogCustom);
        builder.setTitle("Are you sure you completed the task?");

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new DeleteTask().execute(taskList.get(position));
                // dodaj do drugiej listy, wykonanych taskow
                taskList.remove(position);
                notifyItemRemoved(position);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void makeDeleteDialog(View view, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.ThemeDialogCustom);
        builder.setTitle("Are you sure you want to delete the task?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new DeleteTask().execute(taskList.get(position));
                taskList.remove(position);
                notifyItemRemoved(position);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return taskList.size();
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
