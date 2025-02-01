package claudia.task;

import claudia.exception.InvalidFormatException;
import claudia.parser.DateTimeParser;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String fileFormat() {
        return String.format("E | %s | %s | %s | %s", super.isDone() ? "1" : "0",
                super.getDescription(), DateTimeParser.formatForStorage(from),
                DateTimeParser.formatForStorage(to));
    }

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

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                DateTimeParser.parseToString(this.from), DateTimeParser.parseToString(this.to));
    }
}
