package claudia.exception;

/**
 * Represents an exception that occurs when a required description is
 * missing from user input.
 */
public class MissingDescriptionException extends ClaudiaException {

    /**
     * Constructs a MissingDescriptionException with a message
     * specifying the missing command description.
     *
     * @param command The command that requires a description.
     */
    public MissingDescriptionException(String command) {
        super(String.format("The description of a %s cannot be empty.", command));
    }
}