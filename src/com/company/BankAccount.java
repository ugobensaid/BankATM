package com.company;

public class BankAccount {
    private int availableAmount;

    BankAccount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    void deposit(int amount) {
        if (amount % 10 != 0 || amount <= 0)
            System.out.println("You only can deposit an amount of a multiple of 10");
        if (amount % 10 == 0 && amount > 0) {
            availableAmount += amount;
            System.out.println(amount + "€ added to your account.");
        }
        System.out.println("You have now " + availableAmount + "€ on your bank account");
    }

    void withdraw(int amount) {
        if (amount % 10 != 0 || amount <= 0)
            System.out.println("You only can withdraw an amount of a multiple of 10");
        else if (availableAmount >= amount) {
            availableAmount -= amount;
            System.out.println(amount + "€ withdraw from your account.");
        }
        else
            System.out.println("You don't have enough money");
        System.out.println("You have now " + availableAmount + "€ on your bank account");
    }

     void getAccountStatement() {
         //function to print the account statement
         System.out.println("Feature not yet implemented");
    }
}
