package commands;

import exceptions.ClaudiaException;
import exceptions.InvalidFormatException;
import misc.TaskList;
import parser.DateTimeParser;
import storage.Storage;
import tasks.Deadline;
import ui.Ui;

public class DeadlineCommand extends Command {
    public final String description;

    public DeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        Deadline deadline = getDeadline();
        tasks.addTask(deadline);
        storage.save(tasks);
        ui.showDeadline(tasks, deadline);
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    // parse line from CLI
    private Deadline getDeadline() throws InvalidFormatException {
        if (!description.contains("/by")) {
            throw new InvalidFormatException("Invalid deadline format. Use: deadline <task> /by <date>");
        }

        String[] deadlineInfo = description.split("/by", 2);
        if (deadlineInfo.length < 2 || deadlineInfo[0].isEmpty() || deadlineInfo[1].isEmpty()) {
            throw new InvalidFormatException("Invalid deadline format. Use: deadline <task> /by <date>");
        }

        Deadline deadline = new Deadline(deadlineInfo[0].trim(), DateTimeParser.parseDateTime(deadlineInfo[1].trim()));
        return deadline;
    }
}