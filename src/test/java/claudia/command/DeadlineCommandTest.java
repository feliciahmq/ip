package claudia.command;

import claudia.exception.ClaudiaException;
import claudia.exception.InvalidFormatException;
import claudia.parser.DateTimeParser;
import claudia.misc.TaskList;
import claudia.storage.Storage;
import claudia.task.Deadline;
import java.util.ArrayList;

import claudia.task.Task;
import claudia.ui.Ui;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class DeadlineCommandTest {
    private TaskListStub taskListStub;
    private StorageStub storageStub;
    private UiStub uiStub;

    @BeforeEach
    public void setUp() {
        taskListStub = new TaskListStub();
        storageStub = new StorageStub();
        uiStub = new UiStub();
    }

    @Test
    void executeValidDeadline_addsToTaskList() throws ClaudiaException {
        DeadlineCommand c = new DeadlineCommand("return book /by 02/01/2025 1200");
        TaskList tasks = c.execute(taskListStub, uiStub, storageStub);

        assertEquals(1, tasks.size());
        assertTrue(tasks.getTask(0) instanceof Deadline);
        assertEquals("return book", tasks.getTask(0).getDescription());
    }

    @Test
    void executeValidDeadline_savesToStorage() throws ClaudiaException {
        DeadlineCommand c = new DeadlineCommand("return book /by 02/01/2025 1200");
        c.execute(taskListStub, uiStub, storageStub);
        assertTrue(storageStub.savedTasks);
    }

    @Test
    void executeValidDeadline_showsUi() throws ClaudiaException {
        DeadlineCommand c = new DeadlineCommand("return book /by 02/01/2025 1200");
        c.execute(taskListStub, uiStub, storageStub);
        assertTrue(uiStub.displayDeadline);
    }

    @Test
    void isExit_returnsFalse() {
        DeadlineCommand c = new DeadlineCommand("return book /by 02/01/2025 1200");
        assertFalse(c.isExit());
    }

    private static class TaskListStub extends TaskList {
        private final ArrayList<Task> tasks = new ArrayList<>();

        @Override
        public void addTask(Task t) {
            tasks.add(t);
        }

        @Override
        public int size() {
            return tasks.size();
        }

        @Override
        public Task getTask(int i) {
            return tasks.get(i);
        }
    }

    private static class StorageStub extends Storage {
        public boolean savedTasks = false;

        public StorageStub() {
            super("stub.txt");
        }

        @Override
        public void save(TaskList tasks) {
            savedTasks = true;
        }
    }

    private static class UiStub extends Ui {
        public boolean displayDeadline = false;

        @Override
        public void showDeadline(TaskList tasks, Deadline deadline) {
            displayDeadline = true;
        }
    }




}
