package commands;

import exceptions.ClaudiaException;
import exceptions.InvalidFormatException;
import tasks.Deadline;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private static final String LINE = "____________________________________________________________";
    public final String description;

    public DeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public ArrayList<Task> execute(ArrayList<Task> tasks) throws ClaudiaException {
        Deadline deadline = getDeadline();
        tasks.add(deadline);
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + deadline.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
        System.out.println(LINE);

        return tasks;
    }

    private Deadline getDeadline() throws InvalidFormatException {
        if (!description.contains("/by")) {
            throw new InvalidFormatException("Invalid deadline format. Use: deadline <task> /by <date>");
        }

        String[] deadlineInfo = description.split("/by", 2);
        if (deadlineInfo.length < 2 || deadlineInfo[0].isEmpty() || deadlineInfo[1].isEmpty()) {
            throw new InvalidFormatException("Invalid deadline format. Use: deadline <task> /by <date>");
        }

        Deadline deadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
        return deadline;
    }
}