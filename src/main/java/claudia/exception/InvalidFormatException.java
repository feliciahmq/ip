package claudia.exception;

/**
 * Represents an exception that occurs when user input is in an invalid format.
 * This may include:
 * <ul>
 *     <li>Invalid integer type for mark/unmark commands.</li>
 *     <li>Missing "/by" for deadline commands.</li>
 *     <li>Missing "/from" and/or "/to" for event commands.</li>
 * </ul>
 */
public class InvalidFormatException extends ClaudiaException {
    public InvalidFormatException(String message) {
        super(message);
    }
}
