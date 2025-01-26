package commands;

import exceptions.ClaudiaException;
import tasks.Task;

import java.util.ArrayList;

public abstract class Command {
    public abstract ArrayList<Task> execute(ArrayList<Task> tasks) throws ClaudiaException;
}
