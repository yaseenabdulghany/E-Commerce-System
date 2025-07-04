package Models.SpecialProducts;
import Interfaces.IExpirable;
import Models.Product;

public class ExpirableProduct extends Product implements IExpirable {
    private long expirationDate;

    public ExpirableProduct(String name, double price, int quantity, long expirationDate) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        return System.currentTimeMillis() > expirationDate;
    }
}
