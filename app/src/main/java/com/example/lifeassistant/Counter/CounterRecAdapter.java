package com.example.lifeassistant.Counter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeassistant.R;

import java.util.ArrayList;
import java.util.List;

public class CounterRecAdapter extends RecyclerView.Adapter {
    List<Counter> counterList = new ArrayList<>();
    RecyclerView mRecyclerView;
    public CounterRecAdapter(List<Counter> counterList, RecyclerView mRecyclerView) {
        this.counterList = counterList;
        this.mRecyclerView = mRecyclerView;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView counterName;
        public TextView counterCount;
        public ImageButton incButton;
        public ImageButton decButton;
        public MyViewHolder(View pItem) {
            super(pItem);
            counterName = pItem.findViewById(R.id.counterName);
            counterCount = pItem.findViewById(R.id.counterCount);
            incButton = pItem.findViewById(R.id.addCountButton);
            decButton = pItem.findViewById(R.id.decCountButton);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_counter_layout, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = mRecyclerView.getChildLayoutPosition(view);
                Counter item = counterList.get(itemPosition);
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { // ustawianie parametrow countera w widoku
        Counter counter = counterList.get(position);
        ((MyViewHolder) holder).counterName.setText(counter.getName());
        ((MyViewHolder) holder).counterCount.setText( Integer.toString(counter.getCount()) );
    }

    @Override
    public int getItemCount() {
        return counterList.size();
    }
}
