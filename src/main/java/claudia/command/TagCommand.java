package claudia.command;

import java.util.Arrays;
import java.util.LinkedHashSet;

import claudia.exception.ClaudiaException;
import claudia.exception.InvalidFormatException;
import claudia.exception.InvalidTaskNumberException;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.task.Task;
import claudia.ui.Ui;

/**
 * Represents a command to tag a task from the task list.
 */
public class TagCommand extends Command {
    private final String description;

    /**
     * Constructs a TagCommand with the specified task index and tags.
     *
     * @param description The index of the task to be tagged and tags, as a string.
     */
    public TagCommand(String description) {
        this.description = description;
    }

    /**
     * Executes TagCommand by updating a task from the task list,
     * updating storage, and displaying it in the user interface.
     *
     * @param tasks The current list of tasks.
     * @param ui The Ui handler for user interactions.
     * @param storage The storage handler for saving or loading tasks.
     * @return The string output after executing the command.
     * @throws ClaudiaException If the tag format is valid, the index is invalid or the number format is incorrect.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        String[] info = description.split("\\s+");
        System.out.println(description);

        if (info.length < 2) {
            // at least one task number given
            throw new InvalidFormatException("Invalid tag format. Use: tag <task number> <tag1> <tag2>...");
        }

        try {
            int index = Integer.parseInt(info[0].trim()) - 1; // zero-based index
            System.out.println(index);

            if (index < 0 || index >= tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }

            Task task = tasks.getTask(index);

            // multiple tags from index 1 onward

            LinkedHashSet<String> tags = new LinkedHashSet<>();
            Arrays.stream(Arrays.copyOfRange(info, 1, info.length))
                    .map(tag -> tag.replaceAll("^#+", ""))
                    .filter(tag -> !tag.isEmpty())
                    .forEach(tags::add);

            for (String tag : tags) {
                task.addTag(tag);
            }

            tasks.updateTask(task);
            storage.save(tasks);

            return ui.showTag(task, tags);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Invalid number. Use: tag <task number> <tag1> <tag2>...");
        }
    }

    /**
     * Indicates TagCommand will not exit Claudia chatbot.
     *
     * @return <code>false</code> as TagCommand will not terminate Claudia chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
