package claudia.task;

import claudia.exception.InvalidFormatException;
import claudia.parser.DateTimeParser;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlineTest {

    @Test
    void testFileFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 2, 1, 12, 0);
        Deadline deadline = new Deadline("return book", dateTime);

        String expectedFormat = "D | 0 | return book | " + DateTimeParser.formatForStorage(dateTime);
        assertEquals(expectedFormat, deadline.fileFormat());
    }

    @Test
    void testParseFormat() throws InvalidFormatException {
        String format = "D | 1 | return book | 2025-02-01 12:00";
        Deadline deadline = Deadline.parseFormat(format);

        assertTrue(deadline.isDone);
        assertEquals("return book", deadline.getDescription());
        assertEquals(LocalDateTime.of(2025, 2, 1, 12, 0), deadline.by);
    }

    @Test
    void testInvalidFormat_exceptionThrown() {
        String invalidFormat = "D | 0 | invalid format | wrong date format";
        assertThrows(InvalidFormatException.class, () -> {
            Deadline.parseFormat(invalidFormat);
        });
    }

    @Test
    void testToString() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 1, 2, 12, 0);
        Deadline deadline = new Deadline("return book", dateTime);

        String expected = String.format("[D][ ] return book (by: %s)", DateTimeParser.parseToString(dateTime));
        assertEquals(expected, deadline.toString());
    }

    @Test
    void testMarkAsDone() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 1, 2, 12, 0);
        Deadline deadline = new Deadline("return book", dateTime);

        deadline.markAsDone();
        assertTrue(deadline.isDone());
    }

    @Test
    void testMarkAsNotDone() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 1, 2, 12, 0);
        Deadline deadline = new Deadline("return book", dateTime);

        assertFalse(deadline.isDone());
    }
}
