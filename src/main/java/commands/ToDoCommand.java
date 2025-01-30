package commands;

import exceptions.ClaudiaException;
import misc.TaskList;
import storage.Storage;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class ToDoCommand extends Command {
    private static final String LINE = "____________________________________________________________";
    public final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public TaskList execute(TaskList tasks, Storage storage) throws ClaudiaException {
        Todo todo = new Todo(description.trim());
        tasks.addTask(todo);
        storage.save(tasks);
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + todo.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
        System.out.println(LINE);
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}