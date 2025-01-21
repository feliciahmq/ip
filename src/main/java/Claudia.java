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
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                new UnknownInputException().printException();
                continue;
            }

            String[] commands = input.split(" ", 2); // at most 2 parts
            String command = commands[0];

            switch (command) {
                case "bye":
                    print(EXIT);
                    return;
                case "list":
                    displayList();
                    break;
                case "mark":
                case "unmark":
                    handleMarkUnmark(command, commands[1]);
                    break;
                case "todo":
                    if (checkMissingDescription(commands)) {
                        continue;
                    }
                    addTask(new Todo(commands[1]));
                    break;
                case "deadline":
                    if (checkMissingDescription(commands)) {
                        continue;
                    }
                   handleDeadline(commands[1]);
                    break;
                case "event":
                    if (checkMissingDescription(commands)) {
                        continue;
                    }
                    handleEvent(commands[1]);
                    break;
                default:
                    new UnknownInputException().printException();
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

    private static void handleMarkUnmark(String command, String index) {
        try {
            int i = Integer.parseInt(index) - 1; // zero-based
            if (i < 0 || i >= noOfTasks) {
                new InvalidTaskNumberException(noOfTasks).printException();
                return;
            }

            Task t = tasks[i];
            String success =t.markAsDone();
            print(success);
        } catch (NumberFormatException e) {
            new InvalidFormatException("Invalid number.").printException();
        }
    }

    private static void handleDeadline(String desc) {
        if (!desc.contains("/by")) {
            new InvalidFormatException("Invalid deadline format. Use: deadline <task> /by <date>").printException();
            return;
        }

        String[] deadlineInfo = desc.split("/by", 2);
        if (deadlineInfo.length < 2 || deadlineInfo[0].isEmpty() || deadlineInfo[1].isEmpty()) {
            new InvalidFormatException("Invalid deadline format. Use: deadline <task> /by <date>").printException();
            return;
        }

        addTask(new Deadline(deadlineInfo[0], deadlineInfo[1]));
    }

    private static void handleEvent(String desc) {
        // missing /from or /to
        if (!desc.contains("/from") || !desc.contains("/to")) {
            new InvalidFormatException("Invalid event format. Use: event <task> /from <start> /to <end>").printException();
            return;
        }

        String[] eventInfo = desc.split("/from", 2);
        if (eventInfo.length < 2 || eventInfo[0].isEmpty() || eventInfo[1].isEmpty()) {
            new InvalidFormatException("Invalid event format. Use: event <task> /from <start> /to <end>").printException();
            return;
        }

        String[] dateTime = eventInfo[1].split("/to", 2);
        if (dateTime.length < 2 || dateTime[0].isEmpty() || dateTime[1].isEmpty()) {
            new InvalidFormatException("Invalid event format. Use: event <task> /from <start> /to <end>").printException();
            return;
        }

        addTask(new Event(eventInfo[0], dateTime[0], dateTime[1]));
    }

    private static void displayList() {
        if (noOfTasks == 0) {
            new EmptyListException().printException();
            return;
        }

        printLine();
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < noOfTasks; i++) {
            System.out.printf(" %d.%s%n", i + 1, tasks[i].toString());
        }

        printLine();
    }

    private static boolean checkMissingDescription(String[] commands) {
        // missing description, true if missing
        if (commands.length < 2 || commands[1].trim().isEmpty()) {
            new MissingDescriptionException(commands[0]).printException();
            return true;
        }
        return false;
    }
}