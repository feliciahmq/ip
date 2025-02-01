package claudia.parser;

import claudia.exception.InvalidFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses date and time formats for user input, display and storage.
 */
public class DateTimeParser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");
    private static final DateTimeFormatter STORAGE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses a date-time string from user input.
     *
     * @param input The date-time string in "dd/MM/yyy HHmm" format.
     * @return The parsed LocalDateTime object.
     * @throws InvalidFormatException If the input format is invalid.
     */
    public static LocalDateTime parseDateTime(String input) throws InvalidFormatException {
        try {
            return LocalDateTime.parse(input, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Invalid date format. Please use dd/mm/yyy HHmm.");
        }
    }

    /**
     * Formats a LocalDateTime object for display.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return A formatted string int "MMM dd yyyy, HHmm" format.
     */
    public static String parseToString(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FORMATTER);
    }

    /**
     * Formats a LocalDateTime object for storage in file.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return A formatted string in "yyyy-MM-dd HH:mm" format.
     */
    public static String formatForStorage(LocalDateTime dateTime) {
        return dateTime.format(STORAGE_FORMATTER);
    }

    /**
     * Parses a date-time string from storage format.
     *
     * @param input The date-time string in "yyyy-MM-dd HH:mm" format.
     * @return The parsed LocalDateTime object.
     * @throws InvalidFormatException If the format in file is invalid.
     */
    public static LocalDateTime parseFromStorage(String input) throws InvalidFormatException {
        try {
            return LocalDateTime.parse(input, STORAGE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Something went wrong in Date Time Parser.");
        }
    }
}
