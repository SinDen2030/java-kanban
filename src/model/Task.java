package model;

import homework.Progress;
import java.util.Objects;

public class Task {
    protected String name;
    protected String description;
    protected final int id;
    protected Progress status;

    public Progress getStatus() {
        return status;
    }

    public Task(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
        status = Progress.NEW;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    public void setStatus(Progress status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{name=" + name + ", description.length=" + description.length() + ", id=" + id +
                ", status=" + status + "}";
    }
}
