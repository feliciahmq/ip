package claudia.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import claudia.exception.InvalidFormatException;
import claudia.misc.TaskList;
import claudia.task.Task;

/**
 * Handles loading and saving of tasks from and to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given new or updated task list to a file, overwriting any existing data.
     *
     * @param tasks The task list to be saved.
     */
    public void save(TaskList tasks) {
        File folder = new File("./data");

        if (!folder.exists()) {
            folder.mkdir();
        }

        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task t : tasks.getTasks()) {
                fw.write(t.fileFormat() + System.lineSeparator()); // specific claudia.task
            }
        } catch (IOException e) {
            System.out.println("Something went wrong in saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file into an ArrayList, to be used when initiating a new TaskList.
     *
     * @return A list of tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // empty tasks
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                Task t = Task.parseFormat(line); // general Task
                if (t != null) {
                    tasks.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong loading tasks, file not found: " + e.getMessage());
        } catch (InvalidFormatException e) {
            System.out.println("Something went wrong parsing file: " + e.getMessage());
        }

        return tasks;
    }
}
