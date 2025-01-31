package commands;

import exceptions.ClaudiaException;
import misc.TaskList;
import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public abstract class Command {
    public abstract TaskList execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException;

    public abstract boolean isExit();
}
