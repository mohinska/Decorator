package decorator.item;

public class BasicItem implements Item {
    private String name;
    private double basePrice;

    public BasicItem(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    @Override
    public String getDescription() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.basePrice;
    }
}