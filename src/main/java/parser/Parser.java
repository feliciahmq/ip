package parser;

import commands.*;
import exceptions.ClaudiaException;
import exceptions.MissingDescriptionException;
import exceptions.UnknownInputException;

public class Parser {
    public enum CommandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        UNKNOWN;

        public static CommandType fromString(String command) {
            try {
                return CommandType.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return CommandType.UNKNOWN;
            }
        }
    }

    public static Command parse(String input) throws ClaudiaException {
        String[] commands = input.split(" ", 2); // at most 2 parts
        CommandType command = CommandType.fromString(commands[0]); // enums

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
        default:
            throw new UnknownInputException();
        }
    }

    private static void checkMissingDescription(String[] commands) throws ClaudiaException {
        // missing description, true if missing
        if (commands.length < 2 || commands[1].trim().isEmpty()) {
            throw new MissingDescriptionException(commands[0]);
        }
    }
}
