package claudia.command;

import claudia.exception.ClaudiaException;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.task.Todo;
import claudia.ui.Ui;

public class ToDoCommand extends Command {
    private final String description;

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