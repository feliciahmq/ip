package claudia.exception;

public class UnknownInputException extends ClaudiaException {
    public UnknownInputException() {
        super("I don't know what that means :(");
    }
}

// empty input
// claudia.command unknown -> use enums