package tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String fileFormat() {
        return String.format("E | %s | %s | %s-%s", super.isDone() ? "1" : "0", super.getDescription(), this.from, this.to);
    }

    public static Event parseFormat(String format) {
        String[] info = format.split("\\|");
        boolean isDone = info[1].trim().equals("1");
        String desc = info[2].trim();
        String[] dateTime = info[3].split("-");
        String from = dateTime[0].trim();
        String to = dateTime[1].trim();

        Event event = new Event(desc, from, to);
        if (isDone) {
            event.markAsDone();
        }

        return event;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
