package src.main.java.duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class that represents a event task.
 *
 */
public class Event extends Task {
    private LocalDateTime dueDate;
    public Event(String description, String dueDate) {
        this.description = description;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.dueDate = LocalDateTime.parse(dueDate, dateFormat);
    }

    /**
     * To String
     * @return
     */
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + "(at: " + dueDate.format(dateFormat) + ")";
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * This method returns the string to be written for the event.
     */
    public String toWriteString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E " + super.toWriteString() + " | " + dueDate.format(dateFormat);
    }
}
