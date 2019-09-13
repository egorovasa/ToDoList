package ru.egorovasa.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.add);
    }

    public void save(View view) {
        EditText edit = this.findViewById(R.id.name);
        EditText editDescription = this.findViewById(R.id.description);
        Store.getStore().add(new Item(edit.getText().toString().toLowerCase(), Calendar.getInstance(), editDescription.getText().toString().toLowerCase()));
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
