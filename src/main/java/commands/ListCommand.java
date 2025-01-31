package commands;

import exceptions.ClaudiaException;
import exceptions.EmptyListException;
import misc.TaskList;
import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    private static final String LINE = "____________________________________________________________";

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
