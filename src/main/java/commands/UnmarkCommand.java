package commands;

import exceptions.ClaudiaException;
import exceptions.InvalidFormatException;
import exceptions.InvalidTaskNumberException;
import misc.TaskList;
import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private static final String LINE = "____________________________________________________________";
    private final String index;

    public UnmarkCommand(String index) {
        this.index = index;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        try {
            int i = Integer.parseInt(index) - 1; // zero-based
            if (i < 0 || i >= tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }

            Task t = tasks.getTask(i);
            t.markAsNotDone();
            storage.save(tasks);
            ui.showUnmark(t);
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
