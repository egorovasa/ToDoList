package ru.egorovasa.todolist;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.EditText;

        import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    boolean value;
    int index;

    @Override
    protected void onCreate(@Nullable Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.add);

        this.value = getIntent().getBooleanExtra("edit", false);
        if (value) {
            EditText edit = this.findViewById(R.id.item_name);
            EditText editDescription = this.findViewById(R.id.item_description);
            edit.setText(getIntent().getStringExtra("name"));
            editDescription.setText(getIntent().getStringExtra("description"));
        }
        this.index = getIntent().getIntExtra("index", 0);
    }

    public void save(View view) {
        EditText edit = this.findViewById(R.id.item_name);
        EditText editDescription = this.findViewById(R.id.item_description);
        if (value) {
            Item item = Store.getStore().get(index);
            item.setName(edit.getText().toString());
            item.setDescription(editDescription.getText().toString());
            item.setCreated(Calendar.getInstance());
            Store.getStore().replace(index, item);
        } else {
            Item item = new Item(edit.getText().toString(), Calendar.getInstance(), editDescription.getText().toString());
            Store.getStore().add(item);
        }
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}