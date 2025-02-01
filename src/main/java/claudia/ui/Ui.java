package claudia.ui;

import claudia.misc.TaskList;
import claudia.task.Deadline;
import claudia.task.Event;
import claudia.task.Task;
import claudia.task.Todo;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public void showLoadingError() {
        System.out.println("Loading data from storage...");
    }

    public void showWelcome() {
        this.showLine();
        System.out.println(" Hello! I'm Claudia.");
        System.out.println(" What can I do for you?");
        this.showLine();
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showError(String errorMessage) {
        System.out.println("OOPS!!! " + errorMessage);
    }

    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
        this.showLine();
    }

    public void showToDo(TaskList tasks, Todo todo) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + todo.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
    }

    public void showDeadline(TaskList tasks, Deadline deadline) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + deadline.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
    }


    public void showEvent(TaskList tasks, Event event) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + event.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
    }

    public void showDelete(TaskList tasks, Task t) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + t.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
    }

    public void showList(TaskList tasks) {
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf(" %d.%s%n", i + 1, tasks.getTask(i).toString());
        }
    }

    public void showMark(Task t) {
        String success = " Nice! I've marked this task as done:\n" + "  "
                + t.toString();
        System.out.println(success);
    }

    public void showUnmark(Task t) {
        String success = " OK, I've marked this task as not done yet:\n" + "  "
                + t.toString();
        System.out.println(success);
    }

    public void showMatchingTasks(TaskList matchingTasks) {
        if (matchingTasks.isEmpty()) {
            this.showError("No matching tasks found.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.printf(" %d.%s%n", i + 1, matchingTasks.getTask(i).toString());
            }
        }
    }
}
