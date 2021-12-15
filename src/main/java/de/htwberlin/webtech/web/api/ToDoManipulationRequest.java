package de.htwberlin.webtech.web.api;

public class ToDoManipulationRequest {

    private String task;
    private String DueTo;
    private boolean completed;

    public ToDoManipulationRequest(String task, String dueTo, boolean completed) {
        this.task = task;
        this.DueTo = dueTo;
        this.completed = completed;
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
