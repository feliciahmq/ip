import java.util.Scanner;

public class Claudia {
    public static void main(String[] args) {
        String greet = "Hello! I'm Claudia.\n" + " What can I do for you?";
        String exit = " Bye. Hope to see you again soon!";

        print(greet);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                print(exit);
                break;
            }

            print(input);
        }

    }

    private static void print(String s) {
        String output = "____________________________________________________________\n"
                + " " + s + "\n"
                + "____________________________________________________________\n";
        System.out.println(output);
    }
}
