package claudia.exception;

/**
 * Represents an exception that occurs when the user enters an invalid task number.
 */
public class InvalidTaskNumberException extends ClaudiaException {

    /**
     * Constructs an InvalidTaskNumberException with a message specifying the valid range.
     *
     * @param noOfTasks The total number of tasks, used to define the valid range.
     */
    public InvalidTaskNumberException(int noOfTasks) {
        super(String.format("Enter a valid number between 1 to %d", noOfTasks));
    }
}