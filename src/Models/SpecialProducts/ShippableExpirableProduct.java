package Models.SpecialProducts;
import Interfaces.IShippable;

public class ShippableExpirableProduct extends ExpirableProduct implements IShippable {
    private double weight; // Weight in grams

    public ShippableExpirableProduct(String name, double price, int quantity, double weight, long expirationDate) {
        super(name, price, quantity, expirationDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
