package claudia.command;


import claudia.exception.ClaudiaException;
import claudia.exception.InvalidFormatException;
import claudia.misc.TaskList;
import claudia.parser.DateTimeParser;
import claudia.storage.Storage;
import claudia.task.Deadline;
import claudia.ui.Ui;

/**
 * Represents a command to add a Deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;

    /**
     * Constructs a DeadlineCommand with the specified description.
     *
     * @param description The user input describing the Deadline task.
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Executes DeadlineCommand by creating a Deadline task,
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
        Deadline deadline = getDeadline();
        tasks.addTask(deadline);
        storage.save(tasks);
        return ui.showDeadline(tasks, deadline);
    }

    /**
     * Indicates DeadlineCommand will not exit Claudia chatbot.
     *
     * @return <code>false</code> as DeadlineCommand will not terminate Claudia chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses the user input to create a Deadline task.
     *
     * @return A Deadline task created from the user input.
     * @throws InvalidFormatException If the input format is incorrect.
     */
    private Deadline getDeadline() throws InvalidFormatException {
        if (!description.contains("/by")) {
            throw new InvalidFormatException("Invalid deadline format. Use: deadline <task> /by <date>");
        }

        String[] deadlineInfo = description.split("/by", 2);
        if (deadlineInfo.length < 2 || deadlineInfo[0].isEmpty() || deadlineInfo[1].isEmpty()) {
            throw new InvalidFormatException("Invalid deadline format. Use: deadline <task> /by <date>");
        }

        Deadline deadline = new Deadline(deadlineInfo[0].trim(),
                DateTimeParser.parseDateTime(deadlineInfo[1].trim()));
        return deadline;
    }
}
