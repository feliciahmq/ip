package claudia.task;

/**
 * Represents a ToDo task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string of Todo task to save to storage file.
     *
     * @return The file format representation of the Todo task.
     */
    public String fileFormat() {
        return String.format("T | %s | %s", super.isDone() ? "1" : "0", super.getDescription());
    }

    /**
     * Parses a formatted string from storage file into a ToDo object.
     *
     * @param format The formatted string representation of the ToDo task.
     * @return A ToDo task.
     */
    public static Todo parseFormat(String format) {
        String[] info = format.split("\\|");
        boolean isDone = info[1].trim().equals("1");
        String desc = info[2].trim();

        Todo todo = new Todo(desc);
        if (isDone) {
            todo.markAsDone();
        }

        return todo;
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return The formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
