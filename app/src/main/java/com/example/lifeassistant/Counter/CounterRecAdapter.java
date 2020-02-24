package com.example.lifeassistant.Counter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeassistant.R;

import java.util.ArrayList;
import java.util.List;

public class CounterRecAdapter extends RecyclerView.Adapter {
    List<Counter> counterList = new ArrayList<>();
    RecyclerView mRecyclerView;

    public EditText counterName; // editDialog variables
    public EditText counterCount; // needed them to be public

    public CounterRecAdapter(List<Counter> counterList, RecyclerView mRecyclerView) {
        this.counterList = counterList;
        this.mRecyclerView = mRecyclerView;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView counterName;
        public TextView counterCount;
        public ImageButton incButton;
        public ImageButton decButton;
        public ImageButton editButton;
        public ImageButton deleteButton;
        public MyViewHolder(View pItem) {
            super(pItem);
            counterName = pItem.findViewById(R.id.counterName);
            counterCount = pItem.findViewById(R.id.counterCount);
            incButton = pItem.findViewById(R.id.addCountButton);
            decButton = pItem.findViewById(R.id.decCountButton);
            editButton = pItem.findViewById(R.id.editCounterButton);
            deleteButton = pItem.findViewById(R.id.deleteCounterButton);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_counter_layout, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = mRecyclerView.getChildLayoutPosition(view);
                Counter item = counterList.get(itemPosition);
                // when u touch the counter item
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) { // ustawianie parametrow countera w widoku
        ((MyViewHolder) holder).counterName.setText(counterList.get(position).getName());
        ((MyViewHolder) holder).counterCount.setText( Integer.toString(counterList.get(position).getCount()) );
        ((MyViewHolder) holder).decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterList.get(position).decrement();
                ((MyViewHolder) holder).counterCount.setText( Integer.toString(counterList.get(position).getCount()) );
            }
        });
        ((MyViewHolder) holder).incButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterList.get(position).increment();
                ((MyViewHolder) holder).counterCount.setText( Integer.toString(counterList.get(position).getCount()) );
            }
        });
        ((MyViewHolder) holder).deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDeleteDialog(view, position);
            }
        });
        ((MyViewHolder) holder).editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeEditDialog(view, position);
            }
        });
    }
    private void makeEditDialog(View view, final int positionInList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.ThemeDialogCustom);
        builder.setTitle("Edit the counter:");
        View view1 = (LayoutInflater.from(view.getContext()) ).inflate(R.layout.edit_counter_dialog, null);
        builder.setView(view1)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        counterList.get(positionInList).setCount( Integer.parseInt( counterCount.getText().toString()) );
                        counterList.get(positionInList).setName(counterName.getText().toString());
                        notifyItemChanged(positionInList);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        counterCount = (EditText) view1.findViewById(R.id.dialogCount);
        counterName = (EditText) view1.findViewById(R.id.dialogCounterName);
        counterCount.setText(Integer.toString(counterList.get(positionInList).getCount()));
        counterName.setText(counterList.get(positionInList).getName());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void makeDeleteDialog(View view, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.ThemeDialogCustom);
        builder.setTitle("Are you sure you want to delete the counter?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                counterList.remove(position);
                notifyItemRemoved(position);
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
    public int getItemCount() {
        return counterList.size();
    }
}
