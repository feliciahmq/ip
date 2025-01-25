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

    public String markAsDone() {
        if (isDone) {
            return " This task is already marked as done!";
        }
        isDone = true;
        return " Nice! I've marked this task as done:\n" + "  "
                + this.toString();
    }

    public String markAsNotDone() {
        if (!isDone) {
            return " This task is already marked as not done!";
        }

        isDone = false;
        return " OK, I've marked this task as not done yet:\n" + "  "
                + this.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

}
