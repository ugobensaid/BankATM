package com.company;

import java.util.Scanner;

public class ATMActions {
    private static int attempt = 0;
    private static Client bankClient;

    private static boolean isNumber(String str) {
        boolean isNumber = true;

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            isNumber = false;
        }
        return isNumber;
    }

    private static void displayOptions() {
        System.out.println("\n1. Witdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Account statement\n");
    }

    private static void ATMMenu(Scanner scanner) {
        int amount;

        System.out.print("\nPlease, choose one of the choice proposed below or type \"quit\" to finish :");
        displayOptions();
        String operationChoice = scanner.nextLine();

        switch (operationChoice) {
            case "1":
                System.out.print("How much do you want to withdraw (in euro) ? : ");
                try {
                    amount = Integer.valueOf(scanner.nextLine());
                    bankClient.getBankAccount().withdraw(amount);
                } catch(NumberFormatException e) {
                    System.out.println("Please, enter a valid amount");
                }
                break;
            case "2":
                System.out.println("How much do you want to deposit (in euro) ? : ");
                try {
                    amount = Integer.valueOf(scanner.nextLine());
                    bankClient.getBankAccount().deposit(amount);
                } catch(NumberFormatException e) {
                    System.out.println("Please, enter a valid amount");
                }
                break;
            case "3":
                System.out.println("Please wait, we are printing your account statement...");
                bankClient.getBankAccount().getAccountStatement();
                break;
            default:
                if (!operationChoice.equals("quit"))
                    System.out.println("Please, select one of the option displayed on the screen");
                break;
        }

        if (operationChoice.equals("quit")) {
            System.out.println("Goodbye " + bankClient.getName());
            System.out.println("Don't forget to get your credit card");
            return;
        }
        ATMMenu(scanner);
    }

    private static void pinCodeVerification(Scanner scanner) {
        System.out.print("Hello " + bankClient.getName() + ", please enter your pin code : ");
        String pinVerification = scanner.nextLine();
        if (pinVerification.equals(bankClient.getPinCode()))
            ATMMenu(scanner);
        else {
            System.out.println("Wrong pin code\n");
            attempt += 1;
            if (attempt == 3) {
                System.out.println("You have enter a wrong pin 3 times, " +
                        "your credit card has been blocked.\nPlease contact your bank.");
                return;
            }
            pinCodeVerification(scanner);
        }
    }

    static void pinCodeCreation(String name, Scanner scanner) {
        System.out.print("PLease choose a pin code for your credit card : ");
        String pinCode = scanner.nextLine();
        if (!isNumber(pinCode)) {
            System.out.println("Enter a valid pin code please");
            pinCodeCreation(name, scanner);
        }

        BankAccount bankAccount = new BankAccount(0);
        bankClient = new Client(name, pinCode, bankAccount);

        pinCodeVerification(scanner);
    }
}
