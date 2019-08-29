package ru.egorovasa.todolist;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        } Item item = (Item) o;
        return Objects.equals(name, item.name);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode(){
        return Objects.hash(name);
    }
}