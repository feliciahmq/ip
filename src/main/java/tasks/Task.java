package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String fileFormat() {
        return "";
    }

    public static Task parseFormat(String format) {
        if (format.isEmpty()) {
            return null;
        }

        char type = format.charAt(0);

        switch (type) {
        case 'T':
            return Todo.parseFormat(format);
        case 'D':
            return Deadline.parseFormat(format);
        case 'E':
            return Event.parseFormat(format);
        default:
            throw new IllegalArgumentException("Unknown task: " + type);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

}
