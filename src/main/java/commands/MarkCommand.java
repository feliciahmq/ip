package commands;

import exceptions.ClaudiaException;
import exceptions.InvalidFormatException;
import exceptions.InvalidTaskNumberException;
import tasks.Task;

import java.util.ArrayList;

public class MarkCommand extends Command {
    private static final String LINE = "____________________________________________________________";
    private final String index;

    public MarkCommand(String index) {
        this.index = index;
    }

    @Override
    public ArrayList<Task> execute(ArrayList<Task> tasks) throws ClaudiaException {
        try {
            int i = Integer.parseInt(index) - 1; // zero-based
            if (i < 0 || i >= tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }

            Task t = tasks.get(i);
            System.out.println(LINE);
            String success = t.markAsDone();
            System.out.println(success);
            System.out.println(LINE);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Invalid number.");
        }

        return tasks;
    }
}