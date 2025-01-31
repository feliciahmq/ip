package claudia.exception;

public class ClaudiaException extends Exception {
    public ClaudiaException(String message) {
        super(message);
    }
}

// if it is an exception
// extends Exception -> checked exception
// declared in throws or caught in try-catch block
