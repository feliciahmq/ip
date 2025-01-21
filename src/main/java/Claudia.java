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
                case "todo":
                    addTask(new Todo(commands[1]));
                    break;
                case "deadline":
                    String[] info = commands[1].split("/by", 2);
                    addTask(new Deadline(info[0], info[1]));
                    break;
                case "event":
                    String[] details = commands[1].split("/from", 2);
                    String description = details[0];
                    String[] dateTime = details[1].split("/to", 2);
                    addTask(new Event(description, dateTime[0], dateTime[1]));
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
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", noOfTasks);
        printLine();
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
            System.out.printf(" %d.%s%n", i + 1, tasks[i].toString());
        }

        printLine();
    }
}