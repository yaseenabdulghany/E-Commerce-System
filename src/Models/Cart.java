package Models;

import java.util.*;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void add(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException( "Error: Quantity must be positive");
        else if (product.getQuantity() < quantity) throw new IllegalArgumentException( "Error: Not enough stock for " + product.getName());
        else {
            items.put(product, items.getOrDefault(product, 0) + quantity);

        }
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
