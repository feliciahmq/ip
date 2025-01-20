import java.util.Scanner;

public class Claudia {
    private static final Task[] tasks = new Task[100];
    private static int noOfTasks = 0;

    public static void main(String[] args) {
        String greet = " Hello! I'm Claudia.\n What can I do for you?";
        String exit = " Bye. Hope to see you again soon!";

        print(greet);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] commands = input.split(" ");

            switch (commands[0]) {
                case "bye":
                    print(exit);
                    return;
                case "list":
                    displayList();
                    break;
                case "mark":
                    Task t1 = tasks[Integer.parseInt(commands[1]) - 1];
                    print(t1.markAsDone());
                    break;
                case "unmark":
                    Task t2 = tasks[Integer.parseInt(commands[1]) - 1];
                    print(t2.markAsNotDone());
                    break;
                default:
                    addTask(new Task(input));
            }
        }
    }

    private static void print(String s) {
        String output = "____________________________________________________________\n"
                + s + "\n"
                + "____________________________________________________________\n";
        System.out.println(output);
    }

    private static void addTask(Task task) {
        tasks[noOfTasks] = task;
        noOfTasks++;
        print(" added: " + task.toString());
    }

    private static void displayList() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < noOfTasks; i++) {
            output += " " + (i + 1) + "." + tasks[i].toString();
            if (i < noOfTasks - 1) {
                output += "\n";
            }
        }

        print(output);
    }
}