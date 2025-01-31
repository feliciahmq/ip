package commands;

import exceptions.ClaudiaException;
import misc.TaskList;
import storage.Storage;
import tasks.Task;
import tasks.Todo;
import ui.Ui;

import java.util.ArrayList;

public class ToDoCommand extends Command {
    private static final String LINE = "____________________________________________________________";
    public final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        Todo todo = new Todo(description.trim());
        tasks.addTask(todo);
        storage.save(tasks);
        ui.showToDo(tasks, todo);
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}