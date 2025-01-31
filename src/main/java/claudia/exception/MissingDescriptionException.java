package claudia.exception;

public class MissingDescriptionException extends ClaudiaException {
    public MissingDescriptionException(String command) {
        super(String.format("The description of a %s cannot be empty.", command));
    }
}