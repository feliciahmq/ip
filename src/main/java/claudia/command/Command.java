package claudia.command;


import claudia.exception.ClaudiaException;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.ui.Ui;

/**
 * Represents an abstract command that can be executed in Claudia chatbot.
 * Subclasses should implement specific command behaviours.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The current list of tasks.
     * @param ui The Ui handler for user interactions.
     * @param storage The storage handler for saving or loading tasks.
     * @return The string output after executing the command.
     * @throws ClaudiaException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException;

    /**
     * Indicates whether this command should terminate the chatbot.
     *
     * @return <code>true</code> if command is exit command, else <code>false</code>.
     */
    public abstract boolean isExit();
}
