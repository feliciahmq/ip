package claudia.ui;


import claudia.command.Command;
import claudia.exception.ClaudiaException;
import claudia.misc.TaskList;
import claudia.parser.Parser;
import claudia.storage.Storage;

/**
 * The main class for Claudia chatbot.
 * It communicates with Ui, Storage and TaskList to handle user interactions.
 */
public class Claudia {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String DEFAULT_FILE_PATH = "data/claudia.txt";

    /**
     * Constructs a Friday chatbot, initializing user interface, storage and TaskList.
     * Reads and writes to the storage file specified by the file path.
     *
     * @param filePath Path to the storage file.
     */
    public Claudia(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    /**
     * Overloaded constructor with no arguments
     */
    public Claudia() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Runs Claudia chatbot, reading and executing user command
     * till exit command is given by user.
     */
    public void run() {
        boolean isExit = false;
        tasks = new TaskList(storage.load());

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand); // returns specific claudia.command type
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (ClaudiaException e) {
                ui.showError(e.getMessage()); // catch all custom claudia.exceptions here, then print message
            }
        }
    }

    public void guiStart() {
        tasks = new TaskList(storage.load());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            if (command.isExit()) {
                System.exit(0);
            }
            return command.execute(tasks, ui, storage);
        } catch (ClaudiaException e) {
            return e.getMessage();
        }
    }

    /**
     * The main entry point of Claudia chatbot.
     * Initializes and starts the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Claudia("data/claudia.txt").run();
    }
}
