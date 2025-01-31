package tasks;

import exceptions.InvalidFormatException;
import parser.DateTimeParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String fileFormat() {
        return String.format("D | %s | %s | %s", super.isDone() ? "1" : "0",
                super.getDescription(), DateTimeParser.formatForStorage(by));
    }

    // parse line from claudia.txt file
    public static Deadline parseFormat(String format) throws InvalidFormatException {
        String[] info = format.split("\\|");
        boolean isDone = info[1].trim().equals("1");
        String desc = info[2].trim();
        LocalDateTime dateTime = DateTimeParser.parseFromStorage(info[3].trim());

        Deadline deadline = new Deadline(desc, dateTime);
        if (isDone) {
            deadline.markAsDone();
        }

        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), DateTimeParser.parseToString(this.by));
    }
}
