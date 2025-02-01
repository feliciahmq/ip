package claudia.command;

import claudia.exception.ClaudiaException;
import claudia.exception.InvalidFormatException;
import claudia.misc.TaskList;
import claudia.parser.DateTimeParser;
import claudia.storage.Storage;
import claudia.task.Event;
import claudia.ui.Ui;

public class EventCommand extends Command {
    public final String description;

    public EventCommand(String description) {
        this.description = description;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui,Storage storage) throws ClaudiaException {
        Event event = getEvent();
        tasks.addTask(event);
        storage.save(tasks);
        ui.showEvent(tasks, event);
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private Event getEvent() throws InvalidFormatException {
        // missing /from or /to
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new InvalidFormatException("Invalid event format. Use: event <task> /from <start> /to <end>");
        }

        String[] eventInfo = description.split("/from", 2);
        if (eventInfo.length < 2 || eventInfo[0].isEmpty() || eventInfo[1].isEmpty()) {
            throw new InvalidFormatException("Invalid event format. Use: event <task> /from <start> /to <end>");
        }

        String[] dateTime = eventInfo[1].split("/to", 2);
        if (dateTime.length < 2 || dateTime[0].isEmpty() || dateTime[1].isEmpty()) {
            throw new InvalidFormatException("Invalid event format. Use: event <task> /from <start> /to <end>");
        }

        return new Event(eventInfo[0].trim(), DateTimeParser.parseDateTime(dateTime[0].trim()),
                DateTimeParser.parseDateTime(dateTime[1].trim()));
    }
}