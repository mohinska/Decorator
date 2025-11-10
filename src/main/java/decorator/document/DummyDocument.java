package decorator.document;

public class DummyDocument implements Document {
    private final String content;
    private final long delayMs;

    public DummyDocument(String content, long delayMs) {
        this.content = content;
        this.delayMs = delayMs;
    }

    @Override
    public String parse() {
        if (delayMs > 0) {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return content;
    }

    public String getContent() {
        return content;
    }
}
