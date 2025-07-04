package Models;

import java.util.AbstractMap;

public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        if(balance<0) throw  new IllegalArgumentException("Error: Price must be positive number");
        if(name.isEmpty()) throw  new IllegalArgumentException("Error: Name mustn't be empty");
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void decreaseBalance(double amount) {

        if(amount<=0)  throw  new IllegalArgumentException("Error: Decreased amount must be positive ");
        else if(this.balance<amount)  throw   new IllegalArgumentException("Error: Your balance isn't sufficient ");
        else {
            this.balance -= amount;

        }

    }
    public void increaseBalance(double amount){
        if(amount<=0)    throw  new IllegalArgumentException("Error: Increased amount must be positive ");
        else {
            this.balance+=amount;

        }
    }

    public String getName() {
        return name;
    }


}
