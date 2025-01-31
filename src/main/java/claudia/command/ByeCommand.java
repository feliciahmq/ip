package claudia.command;

import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.ui.Ui;

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
