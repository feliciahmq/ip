package exceptions;

public class InvalidTaskNumberException extends ClaudiaException {
    public InvalidTaskNumberException(int noOfTasks) {
        super(String.format("Enter a valid number between 1 to %d", noOfTasks));
    }
}