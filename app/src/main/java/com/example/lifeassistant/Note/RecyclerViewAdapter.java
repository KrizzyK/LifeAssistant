package com.example.lifeassistant.Note;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeassistant.R;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private List<Note> notesList = new ArrayList<>();
    private RecyclerView mRecyclerView;

    public RecyclerViewAdapter(List<Note> notesList, RecyclerView mRecyclerView) {
        this.notesList = notesList;
        this.mRecyclerView = mRecyclerView;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView content;
        public TextView modifiedDate;
        //public TextView createdDate;

        public MyViewHolder(View pItem) {
            super(pItem);
            title = (TextView) pItem.findViewById(R.id.noteTitle);
            content = (TextView) pItem.findViewById(R.id.noteContent);
            modifiedDate = (TextView) pItem.findViewById(R.id.noteModifiedDate);
            //createdDate = (TextView) pItem.findViewById(R.id.cre)
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // tworzymy layout notki oraz obiekt ViewHoldera
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_note_layout, parent, false);
                view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) { // kiedy wciskamy notatke, przechodze do nowego Activity ( otwarta notatka )
                int itemPosition = mRecyclerView.getChildLayoutPosition(view);
                Note item = notesList.get(itemPosition);
                Intent intent = new Intent(view.getContext(), OpenedNoteActivity.class);
                intent.putExtra("passedNote",item);
                intent.putExtra("noteIndex", itemPosition);
                ((Activity)view.getContext()).startActivityForResult(intent, 2); // changing existing one -> 2
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // uzupełniamy layout noty
        Note article = notesList.get(position);
        ((MyViewHolder) holder).title.setText(article.getClusteredNote().getTitle());
        ((MyViewHolder) holder).content.setText(article.getClusteredNote().getContent());
        ((MyViewHolder) holder).modifiedDate.setText(article.getModifiedDateToString());
        //((MyViewHolder) holder).createdDate.setText(article.getCreatedDateToString());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
