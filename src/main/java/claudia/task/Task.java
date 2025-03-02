package claudia.task;

import java.util.LinkedHashSet;

import claudia.exception.InvalidFormatException;

/**
 * Represents a generic Task.
 * A task has a description and completion status.
 * Subclasses include ToDo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected LinkedHashSet<String> tags;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new LinkedHashSet<>();
    }

    /**
     *  Constructs a new Task with the description, completion status and tags.
     *
     * @param description The description of the task.
     * @param isDone If the task is marked or not.
     * @param tags A set of tags of the task.
     */
    public Task(String description, boolean isDone, LinkedHashSet<String> tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     *  Constructs a new Task with the description and tags.
     *
     * @param description The description of the task.
     * @param tags A set of tags of the task.
     */
    public Task(String description, LinkedHashSet<String> tags) {
        this.description = description;
        this.isDone = isDone();
        this.tags = tags;
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

    public void addTag(String tag) {
        tags.add(tag);
    }

    public String getTags() {
        if (tags.isEmpty()) {
            return "";
        }

        StringBuilder tagsString = new StringBuilder();
        for (String tag : tags) {
            tagsString.append(" #").append(tag.replaceAll("^#+", "")).append(" ");
        }

        return tagsString.toString().trim();
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
