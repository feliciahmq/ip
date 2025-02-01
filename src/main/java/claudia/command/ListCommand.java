package claudia.command;

import claudia.exception.ClaudiaException;
import claudia.exception.EmptyListException;

import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.ui.Ui;

public class ListCommand extends Command {

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        if (storage.load().isEmpty()) {
            throw new EmptyListException();
        }

        ui.showList(tasks);

        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
