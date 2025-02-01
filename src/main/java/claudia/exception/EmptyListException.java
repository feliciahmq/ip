package claudia.exception;

/**
 * Represents an exception that occurs when attempting to access an empty task list.
 */
public class EmptyListException extends ClaudiaException {
    public EmptyListException() {
        super("The list is empty~ Add a task now!");
    }
}
