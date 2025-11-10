package decorator.document;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CachedDocumentTest {

    private static final String CACHE_KEY = "gs://test/document/key_unique";

    @BeforeEach
    void setUp() {
        CachedDocument.clearCache();
    }

    @Test
    void cachedDocument_firstCallShouldBeCacheMiss() {
        Document mockDocument = new DummyDocument("Initial Content", 0);
        CachedDocument cachedDoc = new CachedDocument(mockDocument, CACHE_KEY);

        String result = cachedDoc.parse();

        assertEquals("Initial Content", result, "Перший виклик має бути промахом кешу.");
    }

    @Test
    void cachedDocument_secondCallShouldBeCacheHit() {
        Document uniqueDocument = new Document() {
            private int callCount = 0;

            @Override
            public String parse() {
                callCount++;
                return "Result after call #" + callCount;
            }
        };

        CachedDocument cachedDoc = new CachedDocument(uniqueDocument, CACHE_KEY);

        String result1 = cachedDoc.parse();

        String result2 = cachedDoc.parse();

        assertEquals(result1, result2, "Другий виклик має бути з кешу.");
        assertNotEquals("Result after call #2", result2, "Підлеглий документ не повинен був викликатися.");
    }
}