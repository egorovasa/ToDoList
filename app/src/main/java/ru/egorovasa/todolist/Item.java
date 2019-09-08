package ru.egorovasa.todolist;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Calendar;
import java.util.Objects;

public class Item {
    private String name;
    private Calendar created;

    public void setDone(boolean done) {
        this.done = done;
    }

    private boolean done;


    public Item(String name, Calendar created) {
        this.name = name;
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}