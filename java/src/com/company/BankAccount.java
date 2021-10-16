package com.company;

/**
 * Class for bank accounts actions
 */
public class BankAccount {
    private int availableAmount;

    BankAccount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    /**
     * Function to deposit money on the bank account
     * @param amount int
     * @return boolean
     */
    boolean deposit(int amount) {
        if (amount % 10 == 0 && amount > 0) {
            availableAmount += amount;
            System.out.println(amount + "€ added to your account.");
            return true;
        }
        else {
            System.out.println("You only can deposit an amount of a multiple of 10");
            return false;
        }
    }

    /**
     * Function to withdraw money from the bank account
     * @param amount int
     * @return boolean
     */
    boolean withdraw(int amount) {
        if (amount % 10 != 0 || amount <= 0) {
            System.out.println("You only can withdraw an amount of a multiple of 10");
            return false;
        }
        else if (availableAmount >= amount) {
            availableAmount -= amount;
            System.out.println(amount + "€ withdraw from your account.");
            return true;
        }
        else {
            System.out.println("You don't have enough money");
            return false;
        }
    }

    /**
     * Function to check the available amount on the bank account
     * @return int
     */
    int getAvailableAmount() {
        return availableAmount;
    }
}
