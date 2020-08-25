package src.main.java.duke.data.task;

import java.util.Objects;

/**
 * The task class that represents a task.
 *
 * @author Zeng Yu Ting
 * @version 3.0
 * @since 2020-15-08
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {}

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method gets the status icon of the task
     * @return String of status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * This method marks the task as done.
     * @return true value
     */
    public boolean markAsDone() {
        isDone = true;
        return true;
    }

    /**
     * This method marks the task as done.
     * @return true value
     */
    public boolean markAsDoneWithoutPrint() {
        isDone = true;
        return true;
    }

    public int hashCode() {
        return this.description.length();
    }


    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isDone == task.isDone &&
                Objects.equals(description, task.description);
    }

    public String toWriteString() { return " | "+ (isDone ? "1" : "0") + " | " + description;}
}