package com.company;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Please enter your name : ");
        String name = scanner.nextLine();

        ATMActions.pinCodeCreation(name, scanner);
    }
}
