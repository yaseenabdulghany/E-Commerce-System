package Services;

import Interfaces.IExpirable;
import Models.Cart;
import Models.Customer;
import Interfaces.IShippable;
import Models.Product;

import java.util.HashMap;
import java.util.Map;

public class CheckoutService {
    private double shippingFeePerKg = 50;
    private DiscountService discountService;

    public CheckoutService() {
        this.discountService = new DiscountService();
    }

    public void checkout(Customer customer, Cart cart) {

        try
        {
            if (cart.isEmpty()) {
                throw new IllegalArgumentException("Error: Cart is empty");

            }

            double subtotal = 0;
            double shippingFees = 0;
            Map<IShippable,Integer> shippableItems =  new HashMap<>();

            //process cart here
            for (var entry : cart.getItems().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();

                // Check for expiration and stock
                if (product instanceof IExpirable && ((IExpirable) product).isExpired()) {
                    System.out.println("Error: Product " + product.getName() + " is expired.");
                    return;
                }
                if (product.getQuantity() < quantity) {
                    System.out.println("Error: Not enough stock for " + product.getName());
                    return;
                }

                // Calculate subtotal
                subtotal += product.getPrice() * quantity;
                product.decreaseQuantity(quantity); // Reduce stock

                // If shippable, add to the list for shipping
                if (product instanceof IShippable) {
                    shippableItems.put((IShippable) product, shippableItems.getOrDefault((IShippable) product, 0) + quantity);

                }
            }

            // Calculate discounts
            double discount = discountService.applyDiscounts(cart);
            double totalAfterDiscount = subtotal - discount;

            // Calculate shipping fees based on weight
            if (!shippableItems.isEmpty()) {
                double totalWeight = shippableItems.entrySet().stream()
                        .mapToDouble(entry -> entry.getKey().getWeight() * entry.getValue()).sum() / 1000;
                shippingFees = totalWeight * shippingFeePerKg;
            }

            double totalAmount = totalAfterDiscount + shippingFees;

            // Check if the customer has sufficient balance
            if (customer.getBalance() < totalAmount) {
                System.out.println("Error: Insufficient balance.");
                return;
            }
            // Ship items and print ship fees receipt
            ShippingService shippingService = new ShippingService();
            shippingService.ship(shippableItems);

            // Deduct amount and print receipt
            double oldBalance=customer.getBalance();
            customer.decreaseBalance(totalAmount);
            printReceipt(subtotal, discount, shippingFees, totalAmount,customer.getBalance(),oldBalance);
        }
        catch (Exception ex)
        {
            throw ex;
        }

    }

    private void printReceipt
            (double subtotal, double discount, double shippingFees, double totalAmount,double customerBalance,double oldBalance) {
        System.out.println("** Checkout receipt **");
        System.out.printf("Customer Balance before process        %.2f\n", oldBalance );
        System.out.printf("Subtotal         %.2f\n", subtotal);
        System.out.printf("Discount         %.2f\n", discount);
        System.out.printf("Shipping         %.2f\n", shippingFees);
        System.out.printf("Total Amount     %.2f\n", totalAmount);
        System.out.printf("Customer Balance after process        %.2f\n", customerBalance );
    }
}
