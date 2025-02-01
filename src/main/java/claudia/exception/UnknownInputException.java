package claudia.exception;

/**
 * Represents an exception that occurs when the user enters an unknown
 * or invalid command.
 * This may happen due to:
 * <ul>
 *     <li>Empty input.</li>
 *     <li>Unknown command type.</li>
 * </ul>
 */
public class UnknownInputException extends ClaudiaException {
    public UnknownInputException() {
        super("I don't know what that means :(");
    }
}

// empty input
// claudia.command unknown -> use enums