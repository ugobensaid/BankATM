package com.company;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ATMActions {
    private static int attempt = 0;
    private static Client bankClient;
    private static BufferedWriter writer;
    private static File statementFile;

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
        System.out.println("\n1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Account statement");
        System.out.println("4. Quit\n");
    }

    private static String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private static void ATMMenu(Scanner scanner) {
        int amount;

        System.out.print("\nPlease, choose one of the choice proposed below :");
        displayOptions();
        String operationChoice = scanner.nextLine();

        switch (operationChoice) {
            case "1":
                System.out.print("How much do you want to withdraw (in euro) ? : ");
                try {
                    amount = Integer.valueOf(scanner.nextLine());
                    if (bankClient.getBankAccount().withdraw(amount))
                        writer.write(getCurrentDateTime() + ": Withdraw of " + amount + "€\n\n"); //Peut etre faire une fonction qui ecrit dans le fichier et la mettre aussi en bas
                } catch(NumberFormatException | IOException e) {
                    System.out.println("Please, enter a valid amount");
                }
                break;
            case "2":
                System.out.println("How much do you want to deposit (in euro) ? : ");
                try {
                    amount = Integer.valueOf(scanner.nextLine());
                    if (bankClient.getBankAccount().deposit(amount))
                        writer.write(getCurrentDateTime() + ": Deposit of " + amount + "€\n\n");
                } catch(NumberFormatException | IOException e) {
                    System.out.println("Please, enter a valid amount");
                }
                break;
            case "3":
                System.out.println("Please wait, we are printing your account statement...\n");
                //bankClient.getBankAccount().getAccountStatement();
                getAccountStatement();
                System.out.println("Current account balance : " + bankClient.getBankAccount().getAvailableAmount() + "€");
                break;
            case "4":
                System.out.println("Goodbye " + bankClient.getName());
                System.out.println("Don't forget to get your credit card");
                try {
                    writer.close();
                    statementFile.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            default:
                System.out.println("Please, select one of the option displayed on the screen");
                break;
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
        createStatementFile();

        pinCodeVerification(scanner);
    }

    private static void createStatementFile() {
        Path path = FileSystems.getDefault().getPath("./resources").toAbsolutePath();

        try {
            statementFile = new File(path + "/" + "BankStatement.txt");
            if (statementFile.createNewFile()) {
                writer = new BufferedWriter(new FileWriter(statementFile));
                writer.write(bankClient.getName() + "'s Account Statement\n\n");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void getAccountStatement() {
        try {
            writer.flush();
            FileReader fr = new FileReader(statementFile);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
