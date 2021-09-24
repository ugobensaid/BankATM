package com.company;

public class Client {
    private String name;
    private String pinCode;
    private BankAccount bankAccount;

    Client(String name, String pinCode, BankAccount bankAccount) {
        this.name = name;
        this.pinCode = pinCode;
        this.bankAccount = bankAccount;
    }

    String getName() {
        return name;
    }

    String getPinCode() {
        return pinCode;
    }

    BankAccount getBankAccount() {
        return bankAccount;
    }
}
