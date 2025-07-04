package Models;

import com.sun.jdi.connect.Connector;

import java.util.AbstractMap;

public class Product {

    protected String name;
    protected double price;
    protected int quantity;


    public Product(String name, double price, int quantity) {
        if(quantity<0 ) throw new IllegalArgumentException("Error: Quantity must be positive number");
        if(price<0) throw  new IllegalArgumentException("Error: Price must be positive number");
        if(name.isEmpty()) throw  new IllegalArgumentException("Error: name mustn't be empty");
        this.name = name;
        this.price = price;
        this.quantity = quantity;

    }

    public int getQuantity() {return quantity;}

    public void increaseQuantity(int quantity)
    {
        if(quantity>0) this.quantity+=quantity;
        else throw  new IllegalArgumentException("Error:Quantity must be a positive number");

    }

    public void decreaseQuantity(int quantity)
    {
        if(quantity<=0) throw  new IllegalArgumentException("Error:Quantity must be a positive number");
        else if(this.quantity<quantity) throw  new IllegalArgumentException("Error: Insufficient balance");
        else {
            this.quantity-=quantity;

        }
    }

    public double getPrice() {return price;}


    public String getName() {return name;}


}
