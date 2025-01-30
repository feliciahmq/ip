package commands;

import exceptions.ClaudiaException;
import exceptions.InvalidFormatException;
import exceptions.InvalidTaskNumberException;
import misc.TaskList;
import storage.Storage;
import tasks.Task;

import java.util.ArrayList;

public class MarkCommand extends Command {
    private static final String LINE = "____________________________________________________________";
    private final String index;

    public MarkCommand(String index) {
        this.index = index;
    }

    @Override
    public TaskList execute(TaskList tasks, Storage storage) throws ClaudiaException {
        try {
            int i = Integer.parseInt(index) - 1; // zero-based
            if (i < 0 || i >= tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }

            Task t = tasks.getTask(i);
            t.markAsDone();
            storage.save(tasks);

            System.out.println(LINE);
            String success = " Nice! I've marked this task as done:\n" + "  "
                    + t.toString();
            System.out.println(success);
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