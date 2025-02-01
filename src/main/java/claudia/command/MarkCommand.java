package claudia.command;

import claudia.exception.ClaudiaException;
import claudia.exception.InvalidFormatException;
import claudia.exception.InvalidTaskNumberException;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.task.Task;
import claudia.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final String index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done, as a string.
     */
    public MarkCommand(String index) {
        this.index = index;
    }

    /**
     * Executes MarkCommand by marking the specified task as done,
     * updating storage, and displaying it in the user interface.
     *
     * @param tasks The current list of tasks.
     * @param ui The Ui handler for user interactions.
     * @param storage The storage handler for saving or loading tasks.
     * @return The updated task list after marking the task as done.
     * @throws ClaudiaException If the index is invalid or the number format is incorrect.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        try {
            int i = Integer.parseInt(index) - 1; // zero-based
            if (i < 0 || i >= tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }

            Task t = tasks.getTask(i);
            t.markAsDone();
            storage.save(tasks);
            ui.showMark(t);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Invalid number.");
        }

        return tasks;
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