package com.example.worktodo;

public class Model {
    public String id,todo,due,notes,done;

    public Model() {

    }

    public Model(String id, String todo, String due, String notes, String done) {
        this.id = id;
        this.todo = todo;
        this.due = due;
        this.notes = notes;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
