package claudia.exception;

/**
 * Represents an exception that occurs when parsing an invalid date format.
 */
public class DateValidationException extends ClaudiaException {
    public DateValidationException(String message) {
        super(message);
    }
}
