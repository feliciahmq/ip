import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Claudia {
    private static final String GREET = " Hello! I'm Claudia.\n What can I do for you?";
    private static final String EXIT = " Bye. Hope to see you again soon!";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        print(GREET);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();

            try {
                if (input.isEmpty()) {
                    throw new UnknownInputException();
                }

                String[] commands = input.split(" ", 2); // at most 2 parts
                Command command = Command.fromString(commands[0]); // enums

                switch (command) {
                    case BYE:
                        print(EXIT);
                        return;
                    case LIST:
                        displayList();
                        break;
                    case MARK:
                    case UNMARK:
                        checkMissingDescription(commands);
                        handleMarkUnmark(command, commands[1]);
                        break;
                    case TODO:
                        checkMissingDescription(commands);
                        addTask(new Todo(commands[1]));
                        break;
                    case DEADLINE:
                        checkMissingDescription(commands);
                        handleDeadline(commands[1]);
                        break;
                    case EVENT:
                        checkMissingDescription(commands);
                        handleEvent(commands[1]);
                        break;
                    case DELETE:
                        checkMissingDescription(commands);
                        handleDelete(commands[1]);
                        break;
                    default:
                        throw new UnknownInputException();
                }
            } catch (ClaudiaException e) {
                print("OOPS!!! " + e.getMessage()); // catch all custom exceptions here, then print message
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
        tasks.add(task);
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
        printLine();
    }

    private static void displayList() throws ClaudiaException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }

        printLine();
        System.out.println(" Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf(" %d.%s%n", i + 1, tasks.get(i).toString());
        }

        printLine();
    }

    private static void handleMarkUnmark(Command command, String index) throws ClaudiaException {
        try {
            int i = Integer.parseInt(index) - 1; // zero-based
            if (i < 0 || i >= tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }

            Task t = tasks.get(i);
            String success = (command == Command.MARK) ? t.markAsDone() : t.markAsNotDone();
            print(success);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Invalid number.");
        }
    }

    private static void handleDeadline(String desc) throws ClaudiaException {
        if (!desc.contains("/by")) {
            throw new InvalidFormatException("Invalid deadline format. Use: deadline <task> /by <date>");
        }

        String[] deadlineInfo = desc.split("/by", 2);
        if (deadlineInfo.length < 2 || deadlineInfo[0].isEmpty() || deadlineInfo[1].isEmpty()) {
            throw new InvalidFormatException("Invalid deadline format. Use: deadline <task> /by <date>");
        }

        addTask(new Deadline(deadlineInfo[0], deadlineInfo[1]));
    }

    private static void handleEvent(String desc) throws ClaudiaException {
        // missing /from or /to
        if (!desc.contains("/from") || !desc.contains("/to")) {
            throw new InvalidFormatException("Invalid event format. Use: event <task> /from <start> /to <end>");
        }

        String[] eventInfo = desc.split("/from", 2);
        if (eventInfo.length < 2 || eventInfo[0].isEmpty() || eventInfo[1].isEmpty()) {
            throw new InvalidFormatException("Invalid event format. Use: event <task> /from <start> /to <end>");
        }

        String[] dateTime = eventInfo[1].split("/to", 2);
        if (dateTime.length < 2 || dateTime[0].isEmpty() || dateTime[1].isEmpty()) {
            throw new InvalidFormatException("Invalid event format. Use: event <task> /from <start> /to <end>");
        }

        addTask(new Event(eventInfo[0], dateTime[0], dateTime[1]));
    }

    private static void handleDelete(String index) throws ClaudiaException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }

        try {
            int i = Integer.parseInt(index) - 1; // zero-based
            if (i < 0 || i >= tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }

            Task t = tasks.get(i);
            tasks.remove(t);
            printLine();
            System.out.println(" Noted. I've removed this task:");
            System.out.println("  " + t.toString());
            System.out.printf(" Now you have %d tasks in the list.\n", tasks.size());
            printLine();
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Invalid number.");
        }
    }

    private static void checkMissingDescription(String[] commands) throws ClaudiaException {
        // missing description, true if missing
        if (commands.length < 2 || commands[1].trim().isEmpty()) {
            throw new MissingDescriptionException(commands[0]);
        }
    }
}