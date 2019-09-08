package ru.egorovasa.todolist;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
        EditText edit = this.findViewById(R.id.editText);
        this.items.add(new Item(edit.getText().toString().toLowerCase(), Calendar.getInstance()));
        edit.setText("");
        adapter.notifyItemInserted(this.items.size() - 1);
    }

    public void delete(View view) {
        EditText edit = this.findViewById(R.id.editText);
        Item myItem = new Item(edit.getText().toString().toLowerCase(), Calendar.getInstance());
        int index = this.items.indexOf(myItem);
        boolean result = this.items.remove(myItem);
        if (result) {
            edit.setText("");
            adapter.notifyItemRemoved(index);
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
            ) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
            TextView name = holder.itemView.findViewById(R.id.name);
            TextView created = holder.itemView.findViewById(R.id.created);
            TextView dataDone = holder.itemView.findViewById(R.id.dataDone);

            Item item = this.items.get(index);
            name.setText(String.format("%s. %s", index, this.items.get(index).getName()));
            created.setText(format(item.getCreated()));
            CheckBox done = holder.itemView.findViewById(R.id.done);
            done.setOnCheckedChangeListener((view, checked) -> {
                item.setDone(checked);
                dataDone.setText(format(Calendar.getInstance()));
                if (!item.isDone()) {
                    dataDone.setText("");
                }
            });
        }

        private String format(Calendar cal) {
            return String.format(Locale.getDefault(), "%02d.%02d.%d",
                    cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }
    }
}