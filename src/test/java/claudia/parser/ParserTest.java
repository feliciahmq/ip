package claudia.parser;

import claudia.command.Command;
import claudia.command.ByeCommand;
import claudia.command.ToDoCommand;
import claudia.command.DeadlineCommand;
import claudia.command.EventCommand;
import claudia.command.ListCommand;
import claudia.command.MarkCommand;
import claudia.command.UnmarkCommand;
import claudia.command.DeleteCommand;
import claudia.exception.ClaudiaException;
import claudia.exception.MissingDescriptionException;
import claudia.exception.UnknownInputException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {

    @Test
    void parseByeCommand_returnsByeCommand() throws ClaudiaException {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ByeCommand);
    }

    @Test
    void parseListCommand_returnsListCommand() throws ClaudiaException {
        Command c = Parser.parse("list");
        assertTrue(c instanceof ListCommand);
    }

    @Test
    void parseMarkCommand_returnsMarkCommand() throws ClaudiaException {
        Command c = Parser.parse("mark 1");
        assertTrue(c instanceof MarkCommand);
    }

    @Test
    void parseUnmarkCommand_returnsUnmarkCommand() throws ClaudiaException {
        Command c = Parser.parse("unmark 1");
        assertTrue(c instanceof UnmarkCommand);
    }

    @Test
    void parseToDoCommand_returnsToDoCommand() throws ClaudiaException {
        Command c = Parser.parse("todo read book");
        assertTrue(c instanceof ToDoCommand);
    }

    @Test
    void parseDeadlineCommand_returnsDeadlineCommand() throws ClaudiaException {
        Command c = Parser.parse("deadline return book /by 01/02/2025 1200");
        assertTrue(c instanceof DeadlineCommand);
    }

    @Test
    void parseEventCommand_returnsEventCommand() throws ClaudiaException {
        Command c = Parser.parse("event arts night /from 01/02/2025 1100 /to 01/02/2025 1500");
        assertTrue(c instanceof EventCommand);
    }

    @Test
    void parseDeleteCommand_returnsDeleteCommand() throws ClaudiaException {
        Command c = Parser.parse("delete 1");
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    void parseMissingDescription_exceptionThrown() {
        assertThrows(MissingDescriptionException.class, () -> Parser.parse("mark"));
        assertThrows(MissingDescriptionException.class, () -> Parser.parse("unmark"));
        assertThrows(MissingDescriptionException.class, () -> Parser.parse("todo"));
        assertThrows(MissingDescriptionException.class, () -> Parser.parse("deadline"));
        assertThrows(MissingDescriptionException.class, () -> Parser.parse("event"));
        assertThrows(MissingDescriptionException.class, () -> Parser.parse("delete"));
    }

    @Test
    void parseUnknownCommand_exceptionThrown() {
        assertThrows(UnknownInputException.class, () -> Parser.parse("random"));
    }
}