public class InvalidFormatException extends ClaudiaException {
    public InvalidFormatException(String message) {
        super(message);
    }
}

// mark or unmark invalid int type
// deadline missing /by
// event missing /from and/or /to