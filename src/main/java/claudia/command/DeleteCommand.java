package claudia.command;

import claudia.exception.ClaudiaException;
import claudia.exception.EmptyListException;
import claudia.exception.InvalidFormatException;
import claudia.exception.InvalidTaskNumberException;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.task.Task;
import claudia.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    public final String index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted, as a string.
     */
    public DeleteCommand(String index) {
        this.index = index;
    }

    /**
     * Executes DeleteCommand by removing a task from the task list,
     * updating storage, and displaying it in the user interface.
     *
     * @param tasks The current list of tasks.
     * @param ui The Ui handler for user interactions.
     * @param storage The storage handler for saving or loading tasks.
     * @return The updated task list after deleting the specified task.
     * @throws ClaudiaException If the list is empty, the index is invalid or the number format is incorrect.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }

        try {
            int i = Integer.parseInt(index) - 1; // zero-based
            if (i < 0 || i >= tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }

            Task t = tasks.getTask(i);
            tasks.removeTask(i);
            storage.save(tasks);

            ui.showDelete(tasks, t);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Invalid number.");
        }

        return tasks;
    }

    /**
     * Indicates DeleteCommand will not exit Claudia chatbot.
     *
     * @return <code>false</code> as DeleteCommand will not terminate Claudia chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}