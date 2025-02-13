package claudia.task;

import java.util.Arrays;
import java.util.LinkedHashSet;

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
     * Constructs a ToDo task with the description, completion status and tags.
     *
     * @param description The description of the ToDo task.
     * @param isDone If the Todo task is marked or not.
     * @param tags A set of tags of the Todo task.
     */
    public Todo(String description, boolean isDone, LinkedHashSet<String> tags) {
        super(description, isDone, tags);
    }

    /**
     * Returns a formatted string of Todo task to save to storage file.
     *
     * @return The file format representation of the Todo task.
     */
    public String fileFormat() {
        String tagName = Arrays.stream(super.getTags().split("\\s+"))
                .map(tag -> tag.replaceAll("^#+", ""))
                .filter(tag -> !tag.isEmpty())
                .reduce((tag1, tag2) -> tag1 + " " + tag2)
                .orElse("");
        return String.format("T | %s | %s | %s", super.isDone() ? "1" : "0", super.getDescription(), tagName);
    }

    /**
     * Parses a formatted string from storage file into a ToDo object.
     *
     * @param format The formatted string representation of the ToDo task.
     * @return A ToDo task.
     */
    public static Todo parseFormat(String format) {
        String[] info = format.split("\\|");
        boolean isDone = info[1].equals("1");
        String desc = info[2].trim();

        LinkedHashSet<String> tagSet = new LinkedHashSet<>();

        if (info.length > 3 && !info[3].trim().isEmpty()) {
            String[] splitTags = info[3].trim().split("\\s+");
            for (String tag : splitTags) {
                if (!tag.isEmpty()) {
                    String tagName = tag.replaceAll("^#+", "");
                    tagSet.add(tagName);
                }
            }
        }

        Todo todo = new Todo(desc, isDone, tagSet);
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
        return String.format("[T]%s\n%s",
                super.toString(),
                super.getTags()
        );
    }
}
