package commands;

import misc.TaskList;
import storage.Storage;
import tasks.Task;

import java.util.ArrayList;

public class ByeCommand extends Command {
    private static final String LINE = "____________________________________________________________";
    private static final String EXIT = " Bye. Hope to see you again soon!";

    @Override
    public TaskList execute(TaskList tasks, Storage storage) {
        System.out.println(LINE);
        System.out.println(EXIT);
        System.out.println(LINE);
        System.exit(0); // terminate program
        return tasks;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
