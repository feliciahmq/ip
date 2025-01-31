package storage;

import exceptions.ClaudiaException;
import misc.TaskList;
import tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // overwrite file
    public void save(TaskList tasks) {
        File folder = new File("./data");

        if (!folder.exists()) {
            folder.mkdir();
        }

        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task t : tasks.getTasks()) {
                fw.write(t.fileFormat() + System.lineSeparator()); // specific task
            }
        } catch (IOException e) {
            System.out.println("Something went wrong saving tasks: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws ClaudiaException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // empty tasks
        }

        try (Scanner scanner = new Scanner(file)){
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
        }

        return tasks;
    }
}
