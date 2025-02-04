package claudia.task;

import java.time.LocalDateTime;

import claudia.exception.InvalidFormatException;
import claudia.parser.DateTimeParser;

/**
 * Represents a Event task with a description, start time and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time and end time.
     *
     * @param description The description of the Event task.
     * @param from The start date of the Event task.
     * @param to The end date of the Event task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted string of Event task to save to storage file.
     *
     * @return The file format representation of the Event task.
     */
    public String fileFormat() {
        return String.format("E | %s | %s | %s | %s", super.isDone() ? "1" : "0",
                super.getDescription(), DateTimeParser.formatForStorage(from),
                DateTimeParser.formatForStorage(to));
    }

    /**
     * Parses a formatted string from storage file into an Event object.
     *
     * @param format The formatted string representation of the Event task.
     * @return An Event task.
     */
    public static Event parseFormat(String format) throws InvalidFormatException {
        String[] info = format.split("\\|");
        boolean isDone = info[1].trim().equals("1");
        String desc = info[2].trim();
        LocalDateTime from = DateTimeParser.parseFromStorage(info[3].trim());
        LocalDateTime to = DateTimeParser.parseFromStorage(info[4].trim());

        Event event = new Event(desc, from, to);
        if (isDone) {
            event.markAsDone();
        }

        return event;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return The formatted string representing the Event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                DateTimeParser.parseToString(this.from), DateTimeParser.parseToString(this.to));
    }
}
