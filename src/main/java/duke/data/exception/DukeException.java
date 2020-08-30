package src.main.java.duke.data.exception;

/**
 * Duke exception is customised exception class for Bot
 *
 */
public class DukeException extends Exception {
    // Type of exceptions to handle
    // Missing input
    // Nothing is typed
    // Sth else is typed other than todo and

    private String errorMesage;
    public DukeException(String errorMessage) {
        this.errorMesage = errorMessage;
    }

    public String toString() {
        return "☹ OOPS!!! " + errorMesage;
    }
}
