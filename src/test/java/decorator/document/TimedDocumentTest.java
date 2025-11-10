package decorator.document;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimedDocumentTest {

    private static final String TEST_CONTENT = "Document content for timing test.";

    @Test
    void timedDocument_shouldMeasureParsingTimeCorrectly() {
        long expectedDelay = 70;

        Document delayedDocument = new DummyDocument(TEST_CONTENT, expectedDelay);

        TimedDocument timedDoc = new TimedDocument(delayedDocument);

        long startTime = System.currentTimeMillis();
        String result = timedDoc.parse();
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        assertEquals(TEST_CONTENT, result, "TimedDocument має повернути той самий контент.");

        assertTrue(duration >= expectedDelay - 10,
                "Час виконання має бути не менше симульованої затримки.");
    }

    @Test
    void timedDocument_shouldReturnResultImmediatelyForNoDelay() {
        long expectedDelay = 0;
        Document instantDocument = new DummyDocument(TEST_CONTENT, expectedDelay);
        TimedDocument timedDoc = new TimedDocument(instantDocument);

        long startTime = System.currentTimeMillis();
        timedDoc.parse();
        long duration = System.currentTimeMillis() - startTime;

        assertTrue(duration < 5, "Без затримки час виконання має бути мінімальним.");
    }
}
