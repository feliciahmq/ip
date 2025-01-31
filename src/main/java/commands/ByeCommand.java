package commands;

import misc.TaskList;
import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public class ByeCommand extends Command {

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        System.exit(0); // terminate program
        return tasks;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
