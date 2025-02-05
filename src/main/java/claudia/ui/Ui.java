package claudia.ui;

import java.util.Scanner;

import claudia.misc.TaskList;
import claudia.task.Deadline;
import claudia.task.Event;
import claudia.task.Task;
import claudia.task.Todo;

/**
 * Handles messages displayed by chatbot.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a horizontal line separator.
     */
    public String showLine() {
        return "_____________________________________";
    }

    /**
     * Displays a loading message when loading data from storage.
     */
    public String showLoadingError() {
        return "Loading data from storage...";
    }

    /**
     * Displays a welcome message when Claudia chatbot starts.
     */
    public static String showWelcome() {
        return " Hello! I'm Claudia.\n What can I do for you?";
    }

    /**
     * Displays a horizontal line separator.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public String showError(String errorMessage) {
        return "OOPS!!! " + errorMessage;
    }

    /**
     * Displays a goodbye message when Claudia chatbot terminates.
     */
    public String showGoodbye() {
        return " Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message showing the information of the Todo task added.
     *
     * @param tasks The current list of tasks.
     * @param todo The Todo task that was added.
     */
    public String showToDo(TaskList tasks, Todo todo) {
        return " Got it. I've added this task:\n" +
                "  " + todo.toString() + "\n" +
                String.format(" Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Displays a message showing the information of the Deadline task added.
     *
     * @param tasks The current list of tasks.
     * @param deadline The Deadline task that was added.
     */
    public String showDeadline(TaskList tasks, Deadline deadline) {
        return " Got it. I've added this task:\n" +
                "  " + deadline.toString() + "\n" +
                String.format(" Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Displays a message showing the information of the Event task added.
     *
     * @param tasks The current list of tasks.
     * @param event The Event task that was added.
     */
    public String showEvent(TaskList tasks, Event event) {
        return " Got it. I've added this task:\n" +
                "  " + event.toString() + "\n" +
                String.format(" Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Displays a message showing the deletion of a task.
     *
     * @param tasks The current list of tasks.
     * @param task The task that was removed.
     */
    public String showDelete(TaskList tasks, Task task) {
        return " Noted. I've removed this task: \n"
                + "  " + task.toString() + "\n" +
                String.format(" Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Displays all tasks currently in the list.
     *
     * @param tasks The current list of tasks
     */
    public String showList(TaskList tasks) {
        StringBuilder output = new StringBuilder(" Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format(" %d.%s%n\n", i + 1, tasks.getTask(i).toString()));
        }
        return output.toString();
    }

    /**
     * Displays a message showing the task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String showMark(Task task) {
        return " Nice! I've marked this task as done:\n" + "  "
                + task.toString();
    }

    /**
     * Displays a message showing the task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public String showUnmark(Task task) {
        return " OK, I've marked this task as not done yet:\n" + "  "
                + task.toString();
    }

    /**
     * Displays the list of tasks that match a given search criteria.
     * If no tasks are found, an error message is shown.
     *
     * @param matchingTasks The list of tasks that match the search criteria.
     *                      If the list is empty, an error message is displayed.
     */
    public String showMatchingTasks(TaskList matchingTasks, TaskList originalTasks) {
        if (matchingTasks.isEmpty()) {
            return this.showError("No matching tasks found.");
        } else {
            StringBuilder output = new StringBuilder(" Here are the matching tasks in your list:\n");
            for (Task task : matchingTasks.getTasks()) {
                int index = originalTasks.getTasks().indexOf(task);
                output.append(String.format(" %d.%s%n", index + 1, task.toString()));
            }
            return output.toString();
        }
    }
}
