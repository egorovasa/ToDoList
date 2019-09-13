package ru.egorovasa.todolist;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Calendar;
import java.util.Objects;

public class Item {
    private String name, description;
    private Calendar created;
    private boolean done;

    public void setDone(boolean done) {
        this.done = done;
    }

    public Item(String name, Calendar created, String description) {
        this.name = name;
        this.created = created;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isDone() {
        return done;
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