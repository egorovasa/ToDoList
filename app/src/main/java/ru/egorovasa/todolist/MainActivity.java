package ru.egorovasa.todolist;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<Item> items = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new ItemAdapter(this.items);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    public void add(View view) {
        EditText edit = this.findViewById(R.id.recycler);
        this.items.add(new Item(edit.getText().toString()));
        edit.setText("");
        adapter.notifyItemInserted(this.items.size() - 1);
    }

    public void delete(View view) {
        EditText edit = this.findViewById(R.id.recycler);
        int index = 0;
        if (this.items.contains(edit.getText().toString())) {
            index = this.items.indexOf(edit);
            this.items.remove(this.items.remove(edit));
            edit.setText("");
            adapter.notifyItemRemoved(this.items.size() + 1);
            Toast.makeText(this, "Пункт удалён!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Такого пункта в списке нет.", Toast.LENGTH_SHORT).show();
        }
    }

    private static final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<Item> items;

        public ItemAdapter(List<Item> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.items, parent, false)
            ) {};
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
            TextView name = holder.itemView.findViewById(R.id.name);
            name.setText(String.format("%s. %s", index, this.items.get(index).getName()));
        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }
    }
}