package claudia.command;

import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.ui.Ui;
import javafx.application.Platform;

/**
 * Represents the command to exit Claudia chatbot.
 */
public class ByeCommand extends Command {

    /**
     * Executes ByeCommand, displaying a goodbye message and
     * terminating Claudia chatbot.
     *
     * @param tasks The current list of tasks.
     * @param ui The Ui handler for user interactions.
     * @param storage The storage handler for saving or loading tasks.
     * @return The updated task list (unchanged).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Indicates ByeCommand will exit Claudia chatbot.
     *
     * @return <code>true</code> as bye command terminates Claudia chatbot.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
