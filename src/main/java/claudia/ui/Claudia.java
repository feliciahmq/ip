package claudia.ui;

import claudia.command.Command;
import claudia.misc.TaskList;
import claudia.parser.Parser;
import claudia.storage.Storage;

import claudia.exception.ClaudiaException;

public class Claudia {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public static void main(String[] args) {
        new Claudia("data/claudia.txt").run();
    }
}