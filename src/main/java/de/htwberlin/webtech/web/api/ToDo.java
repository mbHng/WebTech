package de.htwberlin.webtech.web.api;

public class ToDo {

    private long id;
    private String task;
    private String DueTo;
    private boolean completed;

    public ToDo(long id, String task, String dueTo, boolean completed) {
        this.id = id;
        this.task = task;
        this.DueTo = dueTo;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDueTo() {
        return DueTo;
    }

    public void setDueTo(String dueTo) {
        DueTo = dueTo;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
