package claudia.task;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;

import claudia.exception.InvalidFormatException;
import claudia.parser.DateTimeParser;

/**
 * Represents a Deadline task with a description and a due date.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the Deadline task.
     * @param by The due date of the Deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with the specified description, due date and a set of tags.
     *
     * @param description The description of the Deadline task.
     * @param by The due date of the Deadline task.
     * @param tags The set of tags of the Deadline task.
     */
    public Deadline(String description, LocalDateTime by, LinkedHashSet<String> tags) {
        super(description, tags);
        this.by = by;
    }

    /**
     * Returns a formatted string of Deadline task to save to storage file.
     *
     * @return The file format representation of the Deadline task.
     */
    public String fileFormat() {
        String tagName = Arrays.stream(super.getTags().split("\\s+"))
                .map(tag -> tag.replaceAll("^#+", ""))
                .filter(tag -> !tag.isEmpty())
                .reduce((tag1, tag2) -> tag1 + " " + tag2)
                .orElse("");
        return String.format("D | %s | %s | %s | %s", super.isDone() ? "1" : "0",
                super.getDescription(), DateTimeParser.formatForStorage(by), tagName);
    }

    /**
     * Parses a formatted string from storage file into a Deadline object.
     *
     * @param format The formatted string representation of the Deadline task.
     * @return A Deadline task.
     */
    public static Deadline parseFormat(String format) throws InvalidFormatException {
        String[] info = format.split("\\|");
        boolean isDone = info[1].trim().equals("1");
        String desc = info[2].trim();
        LocalDateTime dateTime = DateTimeParser.parseFromStorage(info[3].trim());

        LinkedHashSet<String> tagSet = new LinkedHashSet<>();

        if (info.length > 4 && !info[4].trim().isEmpty()) {
            String[] splitTags = info[4].trim().split("\\s+");
            for (String tag : splitTags) {
                if (!tag.isEmpty()) {
                    String tagName = tag.replaceAll("^#+", "");
                    tagSet.add(tagName);
                }
            }
        }

        Deadline deadline = new Deadline(desc, dateTime, tagSet);
        if (isDone) {
            deadline.markAsDone();
        }

        return deadline;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return The formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)\n%s",
                super.toString(),
                DateTimeParser.parseToString(this.by),
                super.getTags()
        );
    }
}
