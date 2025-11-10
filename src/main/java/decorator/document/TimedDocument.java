package decorator.document;

public class TimedDocument extends AbstractDocumentDecorator {
    public TimedDocument(Document document) {
        super(document);
    }

    @Override
    public String parse() {
        long startTime = System.currentTimeMillis();

        String result = super.parse();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Document parsing time: " + duration + " ms");

        return result;
    }
}