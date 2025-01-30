package commands;

import exceptions.ClaudiaException;
import exceptions.EmptyListException;
import exceptions.InvalidFormatException;
import exceptions.InvalidTaskNumberException;
import misc.TaskList;
import storage.Storage;
import tasks.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private static final String LINE = "____________________________________________________________";
    public final String index;

    public DeleteCommand(String index) {
        this.index = index;
    }

    @Override
    public TaskList execute(TaskList tasks, Storage storage) throws ClaudiaException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }

        try {
            int i = Integer.parseInt(index) - 1; // zero-based
            if (i < 0 || i >= tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }

            Task t = tasks.getTask(i);
            tasks.removeTask(i);
            storage.save(tasks);

            System.out.println(LINE);
            System.out.println(" Noted. I've removed this task:");
            System.out.println("  " + t.toString());
            System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
            System.out.println(LINE);
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