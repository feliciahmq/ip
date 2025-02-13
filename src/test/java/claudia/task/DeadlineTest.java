package claudia.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

import claudia.exception.InvalidFormatException;
import claudia.parser.DateTimeParser;

public class DeadlineTest {

    @Test
    void testFileFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 2, 1, 12, 0);
        LinkedHashSet<String> tags = new LinkedHashSet<>();
        tags.add("urgent");
        tags.add("school");

        Deadline deadline = new Deadline("return book", dateTime, tags);

        String expectedFormat = String.format("D | 0 | return book | %s | urgent school",
                DateTimeParser.formatForStorage(dateTime));;
        assertEquals(expectedFormat, deadline.fileFormat());
    }

    @Test
    void testParseFormat() throws InvalidFormatException {
        String format = "D | 1 | return book | 2025-02-01 12:00 | urgent school";
        Deadline deadline = Deadline.parseFormat(format);

        assertTrue(deadline.isDone);
        assertEquals("return book", deadline.getDescription());
        assertEquals(LocalDateTime.of(2025, 2, 1, 12, 0), deadline.by);
        assertTrue(deadline.getTags().contains("urgent"));
        assertTrue(deadline.getTags().contains("school"));
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
        LinkedHashSet<String> tags = new LinkedHashSet<>();
        tags.add("urgent");
        tags.add("school");

        Deadline deadline = new Deadline("return book", dateTime, tags);

        String expected = String.format("[D][  ] return book (by: %s)\n #urgent #school",
                DateTimeParser.parseToString(dateTime));
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
