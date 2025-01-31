package claudia.parser;

import claudia.exception.InvalidFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");
    private static final DateTimeFormatter STORAGE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // from user input eg. 28/02/2024 1800
    public static LocalDateTime parseDateTime(String input) throws InvalidFormatException {
        try {
            return LocalDateTime.parse(input, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Invalid date format. Please use dd/mm/yyy HHmm.");
        }
    }

    // to print eg. Feb 28 2024, 1800
    public static String parseToString(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FORMATTER);
    }

    // for claudia.storage eg. 2024-02-28 18:00
    public static String formatForStorage(LocalDateTime dateTime) {
        return dateTime.format(STORAGE_FORMATTER);
    }

    public static LocalDateTime parseFromStorage(String input) throws InvalidFormatException {
        try {
            return LocalDateTime.parse(input, STORAGE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Something went wrong in Date Time Parser.");
        }
    }
}
