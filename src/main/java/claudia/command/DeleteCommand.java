package claudia.command;

import claudia.exception.ClaudiaException;
import claudia.exception.EmptyListException;
import claudia.exception.InvalidFormatException;
import claudia.exception.InvalidTaskNumberException;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.task.Task;
import claudia.ui.Ui;

public class DeleteCommand extends Command {
    public final String index;

    public DeleteCommand(String index) {
        this.index = index;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}