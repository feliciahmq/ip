package tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String fileFormat() {
        return String.format("T | %s | %s", super.isDone() ? "1" : "0", super.getDescription());
    }

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

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
