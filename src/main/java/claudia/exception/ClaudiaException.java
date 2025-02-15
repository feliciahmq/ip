package claudia.exception;

/**
 * Represents a custom exception for errors in the Claudia chatbot.
 * This is a checked exception and must be handled in a try-catch block
 * or declared in a method's <code>throws</code> clause.
 */
public class ClaudiaException extends Exception {

    /**
     * Constructs a ClaudiaException with the specified error message.
     *
     * @param message The message of the exception.
     */
    public ClaudiaException(String message) {
        super("NOOOOOO \n" + message);
    }
}
