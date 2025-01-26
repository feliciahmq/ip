package commands;

import tasks.Task;

import java.util.ArrayList;

public class ByeCommand extends Command {
    private static final String LINE = "____________________________________________________________";
    private static final String EXIT = " Bye. Hope to see you again soon!";

    @Override
    public ArrayList<Task> execute(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println(EXIT);
        System.out.println(LINE);
        System.exit(0); // terminate program
        return tasks;
    }
}
