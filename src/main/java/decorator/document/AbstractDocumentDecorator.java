package decorator.document;

public abstract class AbstractDocumentDecorator implements Document {
    protected Document document;

    public AbstractDocumentDecorator(Document document) {
        this.document = document;
    }

    @Override
    public String parse() {
        return document.parse();
    }
}