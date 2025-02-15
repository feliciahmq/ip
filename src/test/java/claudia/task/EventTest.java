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

public class EventTest {

    @Test
    void testFileFormat() {
        LocalDateTime from = LocalDateTime.of(2025, 2, 1, 12, 0);
        LocalDateTime to = LocalDateTime.of(2025, 2, 1, 14, 0);
        LinkedHashSet<String> tags = new LinkedHashSet<>();
        tags.add("meeting");
        tags.add("work");

        Event event = new Event("team discussion", from, to, tags);

        String expectedFormat = String.format("E | 0 | team discussion | %s | %s | meeting work",
                DateTimeParser.formatForStorage(from),
                DateTimeParser.formatForStorage(to));
        assertEquals(expectedFormat, event.fileFormat());
    }

    @Test
    void testParseFormat() throws InvalidFormatException {
        String format = "E | 1 | team discussion | 2025-02-01 12:00 | 2025-02-01 14:00 | meeting work";
        Event event = Event.parseFormat(format);

        assertTrue(event.isDone());
        assertEquals("team discussion", event.getDescription());
        assertEquals(LocalDateTime.of(2025, 2, 1, 12, 0), event.from);
        assertEquals(LocalDateTime.of(2025, 2, 1, 14, 0), event.to);
        assertTrue(event.getTags().contains("meeting"));
        assertTrue(event.getTags().contains("work"));
    }

    @Test
    void testInvalidFormat_exceptionThrown() {
        String invalidFormat = "E | 0 | invalid event | wrong date | wrong end date";
        assertThrows(InvalidFormatException.class, () -> {
            Event.parseFormat(invalidFormat);
        });
    }

    @Test
    void testToString() {
        LocalDateTime from = LocalDateTime.of(2025, 1, 2, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 1, 2, 12, 0);
        LinkedHashSet<String> tags = new LinkedHashSet<>();
        tags.add("work");
        tags.add("important");

        Event event = new Event("team sync-up", from, to, tags);

        String expected = String.format("[E][  ] team sync-up (from: %s to: %s)\n#work  #important",
                DateTimeParser.parseToString(from),
                DateTimeParser.parseToString(to));
        assertEquals(expected, event.toString());
    }

    @Test
    void testMarkAsDone() {
        LocalDateTime from = LocalDateTime.of(2025, 1, 2, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 1, 2, 12, 0);
        Event event = new Event("team sync-up", from, to);

        event.markAsDone();
        assertTrue(event.isDone());
    }

    @Test
    void testMarkAsNotDone() {
        LocalDateTime from = LocalDateTime.of(2025, 1, 2, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 1, 2, 12, 0);
        Event event = new Event("team sync-up", from, to);

        assertFalse(event.isDone());
    }
}
