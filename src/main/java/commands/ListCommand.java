package commands;

import exceptions.ClaudiaException;
import exceptions.EmptyListException;
import tasks.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    private static final String LINE = "____________________________________________________________";

    @Override
    public ArrayList<Task> execute(ArrayList<Task> tasks) throws ClaudiaException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }

        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf(" %d.%s%n", i + 1, tasks.get(i).toString());
        }

        System.out.println(LINE);

        System.out.println(" Here are the tasks in your list:");
        return tasks;
    }

}
