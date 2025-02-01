package claudia.ui;

import claudia.misc.TaskList;
import claudia.task.Deadline;
import claudia.task.Event;
import claudia.task.Task;
import claudia.task.Todo;

import java.util.Scanner;

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
    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Displays a loading message when loading data from storage.
     */
    public void showLoadingError() {
        System.out.println("Loading data from storage...");
    }

    /**
     * Displays a welcome message when Claudia chatbot starts.
     */
    public void showWelcome() {
        this.showLine();
        System.out.println(" Hello! I'm Claudia.");
        System.out.println(" What can I do for you?");
        this.showLine();
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
    public void showError(String errorMessage) {
        System.out.println("OOPS!!! " + errorMessage);
    }

    /**
     * Displays a goodbye message when Claudia chatbot terminates.
     */
    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
        this.showLine();
    }

    /**
     * Displays a message showing the information of the Todo task added.
     *
     * @param tasks The current list of tasks.
     * @param todo The Todo task that was added.
     */
    public void showToDo(TaskList tasks, Todo todo) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + todo.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Displays a message showing the information of the Deadline task added.
     *
     * @param tasks The current list of tasks.
     * @param deadline The Deadline task that was added.
     */
    public void showDeadline(TaskList tasks, Deadline deadline) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + deadline.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Displays a message showing the information of the Event task added.
     *
     * @param tasks The current list of tasks.
     * @param event The Event task that was added.
     */
    public void showEvent(TaskList tasks, Event event) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + event.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Displays a message showing the deletion of a task.
     *
     * @param tasks The current list of tasks.
     * @param task The task that was removed.
     */
    public void showDelete(TaskList tasks, Task task) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Displays all tasks currently in the list.
     *
     * @param tasks The current list of tasks
     */
    public void showList(TaskList tasks) {
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf(" %d.%s%n", i + 1, tasks.getTask(i).toString());
        }
    }

    /**
     * Displays a message showing the task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMark(Task task) {
        String success = " Nice! I've marked this task as done:\n" + "  "
                + task.toString();
        System.out.println(success);
    }

    /**
     * Displays a message showing the task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmark(Task task) {
        String success = " OK, I've marked this task as not done yet:\n" + "  "
                + task.toString();
        System.out.println(success);
    }
}
