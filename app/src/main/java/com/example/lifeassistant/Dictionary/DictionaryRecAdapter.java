package com.example.lifeassistant.Dictionary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeassistant.R;

import java.util.ArrayList;
import java.util.List;

class DictionaryRecAdapter extends RecyclerView.Adapter{
    private List<String> defList = new ArrayList<>();
    private RecyclerView dictionaryRecView;
    public DictionaryRecAdapter(List<String> defList, RecyclerView dictionaryRecView) {
        this.defList = defList;
        this.dictionaryRecView = dictionaryRecView;
    }
    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView definition;
        public MyViewHolder(View pItem) {
            super(pItem);
            definition = (TextView) pItem.findViewById(R.id.wordDefinition);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_definition_layout, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = dictionaryRecView.getChildLayoutPosition(view);
                String item = defList.get(itemPosition);
                // when u touch the counter item
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { // ustawianie parametrow countera w widoku
        ((MyViewHolder) holder).definition.setText( defList.get(position) );
    }

    @Override
    public int getItemCount() {
        return defList.size();
    }
}
