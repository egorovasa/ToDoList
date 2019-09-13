package ru.egorovasa.todolist;

import android.content.Intent;
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

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private final RecyclerView.Adapter adapter = new ItemAdapter();

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
        Intent intent = new Intent(this.getApplicationContext(), AddActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {
        EditText edit = this.findViewById(R.id.editText);
        TextView editDescription = this.findViewById(R.id.description);

        Item myItem = new Item(edit.getText().toString().toLowerCase(), Calendar.getInstance(), editDescription.getText().toString().toLowerCase());
        int index = Store.getStore().getAll().indexOf(myItem);
        boolean result = Store.getStore().getAll().remove(myItem);
        if (result) {
            edit.setText("");
            adapter.notifyItemRemoved(index);
            Toast.makeText(this, "Пункт удалён!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Такого пункта в списке нет.", Toast.LENGTH_SHORT).show();
        }
    }

    private static final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
            TextView description = holder.itemView.findViewById(R.id.description);

            Item item = Store.getStore().get(index);
            name.setText(String.format("%s. %s", index, item.getName()));
            created.setText(format(item.getCreated()));
            description.setText(String.format("%s. %s", index, item.getDescription()));
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
            return Store.getStore().size();
        }
    }
}