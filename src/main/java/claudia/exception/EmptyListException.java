package claudia.exception;

public class EmptyListException extends ClaudiaException {
    public EmptyListException() {
        super("The list is empty~ Add a task now!");
    }
}
