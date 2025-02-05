package claudia.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import claudia.exception.ClaudiaException;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.ui.Ui;

/**
 * Represents a command to find tasks in the task list
 * based on one or more keyword.
 */
public class FindCommand extends Command {
    private final Set<String> set;

    /**
     * Constructs a FindCommand with the specified search keywords.
     * Keywords are split by spaces and converted to lowercase.
     *
     * @param keywords The string of search keywords.
     */
    public FindCommand(String... keywords) {
        this.set = Arrays.stream(keywords)
                .filter(keyword -> keyword != null && !keyword.trim().isEmpty())
                .flatMap(keyword -> Arrays.stream(keyword.trim().toLowerCase().split("\\s+")))
                .collect(Collectors.toSet());
    }


    /**
     * Executes the FindCommand by searching for tasks that contain
     * any of the specified keyword and displaying the results in the user interface.
     *
     * @param tasks The current list of tasks.
     * @param ui The Ui handler for user interactions.
     * @param storage The storage handler for saving or loading tasks.
     * @return The string output after executing the command.
     * @throws ClaudiaException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        TaskList matchingTasks = findTasksByKeyword(tasks);
        return ui.showMatchingTasks(matchingTasks, tasks);
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
        return new TaskList(new ArrayList<>(
                tasks.getTasks().stream()
                .filter(task -> set.stream().anyMatch(keyword -> task.getDescription().toLowerCase().contains(keyword)))
                .collect(Collectors.toList())
        ));
    }
}
