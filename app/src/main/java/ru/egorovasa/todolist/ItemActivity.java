package ru.egorovasa.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.item);

        int index = getIntent().getIntExtra("index", 0);
        Item item = Store.getStore().get(index);
        TextView itemName = findViewById(R.id.item_name);
        itemName.setText(item.getName());
        TextView itemDescription = findViewById(R.id.item_description);
        itemDescription.setText(item.getDescription());

        ImageButton editButton = findViewById(R.id.edit_Item);
        editButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(this.getApplicationContext(), AddActivity.class);
            intent.putExtra("edit", true);
            intent.putExtra("index", index);
            intent.putExtra("name", Store.getStore().get(index).getName());
            intent.putExtra("description", Store.getStore().get(index).getDescription());
            startActivity(intent);
        });
    }

}
