package claudia.command;

import claudia.exception.ClaudiaException;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.ui.Ui;

public abstract class Command {
    public abstract TaskList execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException;

    public abstract boolean isExit();
}
