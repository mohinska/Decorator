package decorator.document;

import java.util.HashMap;
import java.util.Map;

public class CachedDocument extends AbstractDocumentDecorator {
    private static final Map<String, String> CACHE = new HashMap<>();

    private final String cacheKey;

    public CachedDocument(Document document, String gcsPath) {
        super(document);
        this.cacheKey = gcsPath;
    }

    @Override
    public String parse() {
        if (CACHE.containsKey(cacheKey)) {
            System.out.println("[Cache HIT] Using cached result for key: " + cacheKey);
            return CACHE.get(cacheKey);
        }

        System.out.println("[Cache MISS] Calling underlying document parse...");
        String result = super.parse();

        CACHE.put(cacheKey, result);
        System.out.println("[Cache SAVE] Result saved to cache for key: " + cacheKey);

        return result;
    }

    public static void clearCache() {
        CACHE.clear();
    }
}