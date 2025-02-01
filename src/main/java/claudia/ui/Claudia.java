package claudia.ui;

import claudia.command.Command;
import claudia.misc.TaskList;
import claudia.parser.Parser;
import claudia.storage.Storage;

import claudia.exception.ClaudiaException;

/**
 * The main class for Claudia chatbot.
 * It communicates with Ui, Storage and TaskList to handle user interactions.
 */
public class Claudia {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Friday chatbot, initializing user interface, storage and TaskList.
     * Reads and writes to the storage file specified by the file path.
     *
     * @param filePath Path to the storage file.
     */
    public Claudia(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (ClaudiaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Claudia chatbot, reading and executing user command
     * till exit command is given by user.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand); // returns specific claudia.command type
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (ClaudiaException e) {
                ui.showError(e.getMessage()); // catch all custom claudia.exceptions here, then print message
            } finally {
                ui.showLine();
            }
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