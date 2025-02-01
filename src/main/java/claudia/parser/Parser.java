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
import claudia.command.FindCommand;

import claudia.exception.ClaudiaException;
import claudia.exception.MissingDescriptionException;
import claudia.exception.UnknownInputException;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Enum representing the possible command types.
     */
    public enum CommandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        UNKNOWN;

        /**
         * Converts a string into a CommandType.
         *
         * @param command The command string.
         * @return The valid CommandType or Unknown if invalid.
         */
        public static CommandType fromString(String command) {
            try {
                return CommandType.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return CommandType.UNKNOWN;
            }
        }
    }

    /**
     * Parses user input and returns the corresponding Command.
     *
     * @param input The user input string.
     * @return A Command object representing the parsed command.
     * @throws ClaudiaException If an error occurs during parsing.
     */
    public static Command parse(String input) throws ClaudiaException {
        String[] commands = input.split(" ", 2);
        CommandType command = CommandType.fromString(commands[0]);

        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            checkMissingDescription(commands);
            return new MarkCommand(commands[1]);
        case UNMARK:
            checkMissingDescription(commands);
            return new UnmarkCommand(commands[1]);
        case TODO:
            checkMissingDescription(commands);
            return new ToDoCommand(commands[1]);
        case DEADLINE:
            checkMissingDescription(commands);
            return new DeadlineCommand(commands[1]);
        case EVENT:
            checkMissingDescription(commands);
            return new EventCommand(commands[1]);
        case DELETE:
            checkMissingDescription(commands);
            return new DeleteCommand(commands[1]);
        case FIND:
            checkMissingDescription(commands);
            return new FindCommand(commands[1]);
        default:
            throw new UnknownInputException();
        }
    }

    /**
     * Checks if a required command description is missing and
     * throws and exception if missing.
     *
     * @param commands The command input split into parts.
     * @throws ClaudiaException If the description is missing.
     */
    private static void checkMissingDescription(String[] commands) throws ClaudiaException {
        // missing description, true if missing
        if (commands.length < 2 || commands[1].trim().isEmpty()) {
            throw new MissingDescriptionException(commands[0]);
        }
    }
}
