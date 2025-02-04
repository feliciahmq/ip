package claudia.command;


import claudia.exception.ClaudiaException;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.task.Todo;
import claudia.ui.Ui;

/**
 * Represents a command to add a ToDo task.
 */
public class ToDoCommand extends Command {
    private final String description;

    /**
     * Constructs a ToDoCommand with the specified description.
     *
     * @param description The user input describing the ToDo task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes ToDoCommand by creating a ToDo task,
     * adding it to the task list, saving to storage, and displaying it
     * in the user interface.
     *
     * @param tasks The current list of tasks.
     * @param ui The Ui handler for user interactions.
     * @param storage The storage handler for saving or loading tasks.
     * @return The updated task list after adding the ToDo.
     * @throws ClaudiaException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClaudiaException {
        Todo todo = new Todo(description.trim());
        tasks.addTask(todo);
        storage.save(tasks);
        return ui.showToDo(tasks, todo);
    }

    /**
     * Indicates ToDoCommand will not exit Claudia chatbot.
     *
     * @return <code>false</code> as ToDoCommand will not terminate Claudia chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
