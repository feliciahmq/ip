package claudia.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import claudia.exception.ClaudiaException;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.task.Task;
import claudia.ui.Ui;

/**
 * Represents a command to find tasks in the task list
 * based on one or more keyword.
 */
public class FindCommand extends Command {
    private final Set<String> keywords;

    /**
     * Constructs a FindCommand with the specified search keywords.
     * Keywords are split by spaces and converted to lowercase.
     *
     * @param string The string of search keywords separated by spaces.
     */
    public FindCommand(String string) {
        this.keywords = new HashSet<>();
        for (String word : string.trim().toLowerCase().split("\\s+")) {
            if (!word.isEmpty()) {
                this.keywords.add(word);
            }
        }
    }


    /**
     * Executes the FindCommand by searching for tasks that contain
     * any of the specified keyword and displaying the results in the user interface.
     *
     * @param tasks The current list of tasks.
     * @param ui The Ui handler for user interactions.
     * @param storage The storage handler for saving or loading tasks.
     * @return A TaskList containing the matching tasks.
     * @throws ClaudiaException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        TaskList matchingTasks = findTasksByKeyword(tasks);
        return ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Indicates FindCommand does not exit Claudia chatbot.
     *
     * @return <code>false</code> as FindCommand does not terminate Claudia chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Searches for tasks that contain any of the keywords in their descriptions.
     *
     * @param tasks The task list to search.
     * @return A TaskList containing tasks that match at least one keyword.
     */
    private TaskList findTasksByKeyword(TaskList tasks) {
        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            String description = task.getDescription().toLowerCase();
            for (String keyword : keywords) {
                if (description.contains(keyword)) {
                    matchedTasks.add(task);
                    break;
                }
            }
        }

        return new TaskList(matchedTasks);
    }
}
