import java.util.Scanner;

public class Claudia {
    private static String[] tasks = new String[100];
    private static int noOfTasks = 0;

    public static void main(String[] args) {
        String greet = " Hello! I'm Claudia.\n What can I do for you?";
        String exit = " Bye. Hope to see you again soon!";

        print(greet);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                print(exit);
                break;
            } else if (input.equals("list")) {
                displayList();
            } else {
                addTask(input);
            }
        }

        scanner.close();
    }

    private static void print(String s) {
        String output = "____________________________________________________________\n"
                + s + "\n"
                + "____________________________________________________________\n";
        System.out.println(output);
    }

    private static void addTask(String task) {
        tasks[noOfTasks] = task;
        noOfTasks++;
        print(" added: " + task);
    }

    private static void displayList() {
        String output = "";
        for (int i = 0; i < noOfTasks; i++) {
            output += " " + (i + 1) + ". " + tasks[i];
            if (i < noOfTasks - 1) {
                output += "\n";
            }
        }
        print(output);
    }
}
