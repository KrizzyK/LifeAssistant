package Note;

import android.view.LayoutInflater;
import android.view.View;
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

        public MyViewHolder(View pItem) {
            super(pItem);
            title = (TextView) pItem.findViewById(R.id.note_title);
            content = (TextView) pItem.findViewById(R.id.note_content);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // tworzymy layout artykułu oraz obiekt ViewHoldera
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);

        // dla elementu listy ustawiamy obiekt OnClickListener,
        // który usunie element z listy po kliknięciu na niego
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // odnajdujemy indeks klikniętego elementu
                int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
                // usuwamy element ze źródła danych
                notesList.remove(positionToDelete);
                // poniższa metoda w animowany sposób usunie element z listy
                notifyItemRemoved(positionToDelete);
            }
        });

        // tworzymy i zwracamy obiekt ViewHolder
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // uzupełniamy layout artykułu
        Note article = notesList.get(position);
        ((MyViewHolder) holder).title.setText(article.getTitle());
        ((MyViewHolder) holder).content.setText(article.getContent());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
