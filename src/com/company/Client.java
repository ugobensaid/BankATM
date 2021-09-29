package com.company;

/**
 * Class Client that contains the necessaries client infos
 */
public class Client {
    private String name;
    private String pinCode;
    private BankAccount bankAccount;

    Client(String name, String pinCode, BankAccount bankAccount) {
        this.name = name;
        this.pinCode = pinCode;
        this.bankAccount = bankAccount;
    }

    /**
     *
     * @return String : the client name
     */
    String getName() {
        return name;
    }

    /**
     *
     * @return String : pin code of the client credit card
     */
    String getPinCode() {
        return pinCode;
    }

    /**
     *
     * @return BankAccount : the bank account of the client
     */
    BankAccount getBankAccount() {
        return bankAccount;
    }
}
