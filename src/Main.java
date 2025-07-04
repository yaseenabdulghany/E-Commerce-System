import Models.*;
import Models.SpecialProducts.ShippableExpirableProduct;
import Models.SpecialProducts.ShippableProduct;
import Services.*;

public class Main {
    public static void main(String[] args) {
        //Idea: some products can expire and some need shipping so i made separate interfaces for that
        //special product classes that use these interfaces with the normal product stuff
        //Error Handling: used try-catch to make it easier to catch errors in one place

        ///////////////////////
        // Test 1 (valid)
        System.out.println("Test 1");
        try {
            ShippableExpirableProduct cheese = new ShippableExpirableProduct
                    ("Cheese", 200, 10, 200, System.currentTimeMillis() + 86400000); // 1 day expiration
            ShippableExpirableProduct biscuits = new ShippableExpirableProduct
                    ("Biscuits", 150, 20, 700, System.currentTimeMillis() + 86400000); // 1 day expiration
            ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight
            // Create a customer
            Customer customer = new Customer("Mustafa", 3000.0);
            // Create a cart and add products
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(biscuits, 1);
            cart.add(scratchCard, 1);

            // Perform checkout
            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(customer, cart);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        /////////////////////////////
        //Test 2 (valid)
        System.out.println("\nTest 2");
        try {
            ShippableExpirableProduct cheese = new ShippableExpirableProduct
                    ("Cheese", 100, 10, 400, System.currentTimeMillis() + 86400000); // 1 day expiration
            ShippableProduct tv = new ShippableProduct("TV", 1000, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight
            // Create a customer
            Customer customer = new Customer("Mustafa", 10000.0);
            // Create a cart and add products
            Cart cart = new Cart();
            cart.add(cheese, 3);
            cart.add(tv, 4);
            cart.add(scratchCard, 2);

            // Perform checkout
            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(customer, cart);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //////////////////////////////
        /////////////////////////////
        //Test 3 (no Shippable products: valid)
        System.out.println("\nTest 3");
        try {
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight
            // Create a customer
            Customer customer = new Customer("Mustafa", 10000.0);
            // Create a cart and add products
            Cart cart = new Cart();

            cart.add(scratchCard, 2);

            // Perform checkout
            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(customer, cart);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

//////////////////////////////////////////////////////
        //Test 4 (insuffecient stock:Not valid-->fail)
        System.out.println("\nTest 4");
        try {
            ShippableExpirableProduct cheese = new ShippableExpirableProduct
                    ("Cheese", 200, 10, 400, System.currentTimeMillis() + 86400000); // 1 day expiration
            ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight
            // Create a customer
            Customer customer = new Customer("Mustafa", 2000.0);
            // Create a cart and add products
            Cart cart = new Cart();
            cart.add(cheese, 11);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);
            // Perform checkout
            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(customer, cart);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //////////////////////////////////////
        //Test 5 (Empty Cart:Not valid-->fail)
        System.out.println("\nTest 5");
        try {
            ShippableExpirableProduct cheese = new ShippableExpirableProduct
                    ("Cheese", 200, 10, 400, System.currentTimeMillis() + 86400000); // 1 day expiration
            ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight
            // Create a customer
            Customer customer = new Customer("Mustafa", 2000.0);
            // Create a cart and add products
            Cart cart = new Cart();

            // Perform checkout
            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(customer, cart);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        ////////////////////////////
        //Test 6 ( Insufficient Balance:Invalid)
        System.out.println("\nTest 6");
        try {
            ShippableExpirableProduct cheese = new ShippableExpirableProduct
                    ("Cheese", 200, 10, 400, System.currentTimeMillis() + 86400000); // 1 day expiration
            ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight
            // Create a customer
            Customer customer = new Customer("Mustafa", 1999.0);
            // Create a cart and add products
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);

            // Perform checkout
            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(customer, cart);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        /////////////////
        //Test 7 ( Expired:Invalid)
        System.out.println("\nTest 7");
        try {
            ShippableExpirableProduct cheese = new ShippableExpirableProduct
                    ("Cheese", 200, 10, 400, System.currentTimeMillis() - 86400000); // 1 day expiration
            ShippableProduct tv = new ShippableProduct("TV", 1500, 5, 8000); // No expiration
            Product scratchCard = new Product("Mobile Scratch Card", 50, 20); // No expiration and No weight
            // Create a customer
            Customer customer = new Customer("Mustafa", 2000.0);
            // Create a cart and add products
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);

            // Perform checkout
            CheckoutService checkoutService = new CheckoutService();
            checkoutService.checkout(customer, cart);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}

