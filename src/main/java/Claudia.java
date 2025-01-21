import java.util.Scanner;

public class Claudia {
    private static final String GREET = " Hello! I'm Claudia.\n What can I do for you?";
    private static final String EXIT = " Bye. Hope to see you again soon!";
    private static final Task[] tasks = new Task[100];
    private static int noOfTasks = 0;

    public static void main(String[] args) {
        print(GREET);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] commands = input.split(" ", 2);

            switch (commands[0]) {
                case "bye":
                    print(EXIT);
                    return;
                case "list":
                    displayList();
                    break;
                case "mark":
                    markTask(Integer.parseInt(commands[1]) - 1);
                    break;
                case "unmark":
                    unmarkTask(Integer.parseInt(commands[1]) - 1);
                    break;
                default:
                    addTask(new Task(input));
            }
        }
    }

    private static void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    private static void print(String s) {
        printLine();
        System.out.println(s);
        printLine();
    }

    private static void addTask(Task task) {
        tasks[noOfTasks] = task;
        noOfTasks++;
        print(" added: " + task.toString());
    }

    private static void markTask(int index) {
        Task t = tasks[index];
        String success =t.markAsDone();
        print(success);
    }

    private static void unmarkTask(int index) {
        Task t = tasks[index];
        String success = t.markAsNotDone();
        print(success);
    }

    private static void displayList() {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < noOfTasks; i++) {
            System.out.printf(" %d. %s%n", i + 1, tasks[i].toString());
        }

        printLine();
    }
}