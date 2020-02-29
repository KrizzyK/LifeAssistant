package com.example.lifeassistant.ToDoStack;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeassistant.R;

import java.util.List;

class TaskAdapter extends RecyclerView.Adapter {

    private List<Task> taskList;
    private List<Task> donetasksList;
    private RecyclerView taskRecView;

    public TaskAdapter(List<Task> taskList,List<Task> donetasksList, RecyclerView taskRecView) {
        this.taskList = taskList;
        this.taskRecView = taskRecView;
        this.donetasksList = donetasksList;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton taskDoneButton, editTaskButton, deleteTaskButton;
        TextView deadlineDate, taskName, timeLeft;

        public MyViewHolder(View pItem) {
            super(pItem);
            deadlineDate = (TextView) pItem.findViewById(R.id.deadLineDate);
            taskName = (TextView) pItem.findViewById(R.id.taskName);
            timeLeft = (TextView) pItem.findViewById(R.id.timeLeft);
            taskDoneButton = (ImageButton) pItem.findViewById(R.id.taskDoneButton);
            editTaskButton = (ImageButton) pItem.findViewById(R.id.editTaskButton);
            deleteTaskButton = (ImageButton) pItem.findViewById(R.id.deleteTaskButton);
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        // uzupelniamy informacje o taskach w widoku z listy taskList
        Task item = taskList.get(position);
        ((MyViewHolder) holder).taskName.setText(item.getTaskInfo());
        ((MyViewHolder) holder).deadlineDate.setText("Deadline: "+ item.deadlineDate.toString());
        ((MyViewHolder) holder).timeLeft.setText("Time left: " + item.getTimeLeft());
        ((MyViewHolder) holder).deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDeleteDialog(view, position);
            }
        });
        ((MyViewHolder) holder).editTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeEditDialog(view, position);
            }
        });
        ((MyViewHolder) holder).taskDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDoneDialog(view,position);
            }
        });

    }

    private void makeEditDialog(View view, int position) { //////////////////////////TODO -> wzorujac sie na counterze, zrobic custom dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.ThemeDialogCustom);
        builder.setTitle("EDIT?");

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
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

    private void makeDoneDialog(View view, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.ThemeDialogCustom);
        builder.setTitle("Are you sure you completed the task?");

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //new CounterRecAdapter.DeleteCounter().execute(counterList.get(position));
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
                //new CounterRecAdapter.DeleteCounter().execute(counterList.get(position));
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
}
