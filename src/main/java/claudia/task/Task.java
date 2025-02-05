package claudia.task;

import claudia.exception.InvalidFormatException;

/**
 * Represents a generic Task.
 * A task has a description and completion status.
 * Subclasses include ToDo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "  "); // mark done claudia.task with X
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return <code>true</code> if the task is done, else <code>false</code>.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a formatted string of the task to save to storage file.
     *
     * @return The file format representation of the task.
     */
    public String fileFormat() {
        return "";
    }

    /**
     * Parses a formatted string from storage file into a Task object.
     * The method identifies the task type (ToDo, Deadline or Event) based on the string prefix
     * and assign the specific subclass to create the Task object.
     *
     * @param format The formatted string representation of the task.
     * @return A specific Task subclass.
     * @throws InvalidFormatException If task type is unknown.
     */
    public static Task parseFormat(String format) throws InvalidFormatException {
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

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted string representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

}
