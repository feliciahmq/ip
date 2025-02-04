package claudia.command;


import claudia.exception.ClaudiaException;
import claudia.exception.EmptyListException;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes ListCommand by displaying all tasks in the task list.
     *
     * @param tasks The current list of tasks.
     * @param ui The Ui handler for user interactions.
     * @param storage The storage handler for saving or loading tasks.
     * @return The task list (unchanged).
     * @throws ClaudiaException If the task list is empty.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        if (storage.load().isEmpty()) {
            throw new EmptyListException();
        }

        ui.showList(tasks);

        return tasks;
    }

    /**
     * Indicates ListCommand will not exit Claudia chatbot.
     *
     * @return <code>false</code> as ListCommand will not terminate Claudia chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
