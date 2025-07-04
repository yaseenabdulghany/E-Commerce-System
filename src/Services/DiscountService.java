package Services;

import Models.Cart;

public class DiscountService {
    public double applyDiscounts(Cart cart) {
        double discount = 0.0;


        double subtotal = cart.getItems().entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();


        if (subtotal > 300 && subtotal<600) {
            discount = subtotal *  0.10;
        }
        else if(subtotal>=600)
        {
            discount=subtotal*  0.20;
        }
        return discount;
    }
}
