public class ClaudiaException {
    private final String message;

    public ClaudiaException(String message) {
        this.message = message;
    }

    public void printException() {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(" OOPS!!! " + message);
        System.out.println(line);
    }
}

// if it is an exception, not a custom class in this case:
// extends Exception -> checked exception
// declared in throws or caught in try-catch block
