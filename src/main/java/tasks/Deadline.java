package tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String fileFormat() {
        return String.format("D | %s | %s | %s", super.isDone() ? "1" : "0", super.getDescription(), this.by);
    }

    public static Deadline parseFormat(String format) {
        String[] info = format.split("\\|");
        boolean isDone = info[1].trim().equals("1");
        String desc = info[2].trim();
        String dateTime = info[3].trim();

        Deadline deadline = new Deadline(desc, dateTime);
        if (isDone) {
            deadline.markAsDone();
        }

        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
