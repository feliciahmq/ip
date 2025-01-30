package commands;

import exceptions.ClaudiaException;
import misc.TaskList;
import storage.Storage;
import tasks.Task;

import java.util.ArrayList;

public abstract class Command {
    public abstract TaskList execute(TaskList tasks, Storage storage) throws ClaudiaException;

    public abstract boolean isExit();
}
