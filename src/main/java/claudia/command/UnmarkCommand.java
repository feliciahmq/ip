package claudia.command;

import claudia.exception.ClaudiaException;
import claudia.exception.InvalidFormatException;
import claudia.exception.InvalidTaskNumberException;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.task.Task;
import claudia.ui.Ui;


/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final String index;

    /**
     * Constructs a UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as not done, as a string.
     */
    public UnmarkCommand(String index) {
        this.index = index;
    }

    /**
     * Executes UnmarkCommand by marking the specified task as not done,
     * updating storage, and displaying it in the user interface.
     *
     * @param tasks The current list of tasks.
     * @param ui The Ui handler for user interactions.
     * @param storage The storage handler for saving or loading tasks.
     * @return The string output after executing the command.
     * @throws ClaudiaException If the index is invalid or the number format is incorrect.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        try {
            int i = Integer.parseInt(index) - 1; // zero-based
            if (i < 0 || i >= tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }

            Task t = tasks.getTask(i);
            t.markAsNotDone();
            storage.save(tasks);
            return ui.showUnmark(t);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Invalid number. Use: unmark <task number>");
        }
    }

    /**
     * Indicates MarkCommand will not exit Claudia chatbot.
     *
     * @return <code>false</code> as MarkCommand will not terminate Claudia chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
