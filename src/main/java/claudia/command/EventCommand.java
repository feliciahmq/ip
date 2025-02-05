package claudia.command;


import claudia.exception.ClaudiaException;
import claudia.exception.InvalidFormatException;
import claudia.misc.TaskList;
import claudia.parser.DateTimeParser;
import claudia.storage.Storage;
import claudia.task.Event;
import claudia.ui.Ui;

/**
 * Represents a command to add an Event task.
 */
public class EventCommand extends Command {
    private final String description;

    /**
     * Constructs a EventCommand with the specified description.
     *
     * @param description The user input describing the Event task.
     */
    public EventCommand(String description) {
        assert description != null : "Event description cannot be null";
        this.description = description;
    }

    /**
     * Executes EventCommand by creating a Event task,
     * adding it to the task list, saving to storage, and displaying it
     * in the user interface.
     *
     * @param tasks The current list of tasks.
     * @param ui The Ui handler for user interactions.
     * @param storage The storage handler for saving or loading tasks.
     * @return The string output after executing the command.
     * @throws ClaudiaException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        Event event = getEvent();
        tasks.addTask(event);
        storage.save(tasks);
        return ui.showEvent(tasks, event);
    }

    /**
     * Indicates EventCommand will not exit Claudia chatbot.
     *
     * @return <code>false</code> as EventCommand will not terminate Claudia chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses the user input to create an Event task.
     *
     * @return An Event task created from the user input.
     * @throws InvalidFormatException If the input format is incorrect.
     */
    private Event getEvent() throws InvalidFormatException {
        assert description != null : "Event description cannot be null";

        // missing /from or /to
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new InvalidFormatException("Invalid event format. Use: event <task> /from <start> /to <end>");
        }

        String[] eventInfo = description.split("/from", 2);
        assert eventInfo.length == 2 : "Event description should be split into exactly two parts";

        if (eventInfo.length < 2 || eventInfo[0].isEmpty() || eventInfo[1].isEmpty()) {
            throw new InvalidFormatException("Invalid event format. Use: event <task> /from <start> /to <end>");
        }

        String[] dateTime = eventInfo[1].split("/to", 2);
        assert eventInfo.length == 2 : "Event date time should be split into exactly two parts";

        if (dateTime.length < 2 || dateTime[0].isEmpty() || dateTime[1].isEmpty()) {
            throw new InvalidFormatException("Invalid event format. Use: event <task> /from <start> /to <end>");
        }

        return new Event(eventInfo[0].trim(), DateTimeParser.parseDateTime(dateTime[0].trim()),
                DateTimeParser.parseDateTime(dateTime[1].trim()));
    }
}
