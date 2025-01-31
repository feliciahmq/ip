package commands;

import exceptions.ClaudiaException;
import exceptions.EmptyListException;
import misc.TaskList;
import storage.Storage;
import tasks.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    private static final String LINE = "____________________________________________________________";

    @Override
    public TaskList execute(TaskList tasks, Storage storage) throws ClaudiaException {
        if (storage.load().isEmpty()) {
            throw new EmptyListException();
        }

        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf(" %d.%s%n", i + 1, tasks.getTask(i).toString());
        }

        System.out.println(LINE);

        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
