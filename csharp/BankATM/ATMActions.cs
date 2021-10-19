using System;

namespace BankATM
{
    public class ATMActions
    {
        private static int attempt = 0;
        private static Client bankClient;
        private static string accountStatement = "";

        private static bool isNumber(string str)
        {
            int number;

            return int.TryParse(str, out number);
        }

        private static void displayOptions()
        {
            Console.WriteLine("\n1. Withdraw");
            Console.WriteLine("2. Deposit");
            Console.WriteLine("3. Account statement");
            Console.WriteLine("4. Quit\n");
        }

        private static string getCurrentDate()
        {
            return DateTime.Now.ToString("dd/MM/yyyy");
        }

        private static void ATMMenu()
        {
            int amount;

            Console.Write("\nPlease, choose one of the choice proposed below :");
            displayOptions();
            String operationChoice = Console.ReadLine();

            switch (operationChoice)
            {
                case "1":
                    Console.Write("How much do you want to withdraw (in euro) ? : ");
                    try
                    {
                        amount = Int32.Parse(Console.ReadLine());
                        int amountLength = amount.ToString().Length;

                        if (bankClient.getBankAccount().withdraw(amount))
                            addOperationToSTatement("\n WITHDRAW   | " + getCurrentDate() + " | " + amount +
                                printSpaces(amount) + " | " + bankClient.getBankAccount().getAvailableAmount());
                    }
                    catch (FormatException e)
                    {
                        Console.WriteLine("Please, enter a valid amount");
                    }
                    break;
                case "2":
                    Console.WriteLine("How much do you want to deposit (in euro) ? : ");
                    try
                    {
                        amount = Int32.Parse(Console.ReadLine());

                        if (bankClient.getBankAccount().deposit(amount))
                            addOperationToSTatement("\n DEPOSIT    | " + getCurrentDate() + " | " + amount +
                                printSpaces(amount) + " | " + bankClient.getBankAccount().getAvailableAmount());
                    }
                    catch (FormatException e)
                    {
                        Console.WriteLine("Please, enter a valid amount");
                    }
                    break;
                case "3":
                    Console.WriteLine("Please wait, we are printing your account statement...\n");
                    Console.WriteLine(getAccountStatement());
                    break;
                case "4":
                    Console.WriteLine("Goodbye " + bankClient.getName());
                    Console.WriteLine("Don't forget to get your credit card");
                    return;
                default:
                    Console.WriteLine("Please, select one of the option displayed on the screen");
                    break;
            }

            ATMMenu();
        }

        private static void pinCodeVerification()
        {
            Console.Write("Hello " + bankClient.getName() + ", please enter your pin code : ");
            string pinVerification = Console.ReadLine();
            if (String.Equals(pinVerification, bankClient.getPinCode()))
                ATMMenu();
            else
            {
                Console.WriteLine("Wrong pin code\n");
                attempt += 1;
                if (attempt == 3)
                {
                    Console.WriteLine("You have enter a wrong pin 3 times, " +
                            "your credit card has been blocked.\nPlease contact your bank.");
                    return;
                }
                pinCodeVerification();
            }
        }

        public static void pinCodeCreation(string name)
        {
            Console.Write("PLease choose a pin code for your credit card : ");
            string pinCode = Console.ReadLine();
            if (!isNumber(pinCode))
            {
                Console.WriteLine("Enter a valid pin code please");
                pinCodeCreation(name);
            }

            BankAccount bankAccount = new BankAccount(0);
            bankClient = new Client(name, pinCode, bankAccount);

            accountStatement += " Operation  | Date       | Amount     | Balance    \n" +
                " -----------------------------------------------";

            pinCodeVerification();
        }

        private static void addOperationToSTatement(string operation)
        {
            accountStatement += operation;
        }

        private static string getAccountStatement()
        {
            return accountStatement;
        }

        private static string printSpaces(int amount)
        {
            int amountLength = amount.ToString().Length;
            string spaces = "";

            for (int i = 0; i <= 9 - amountLength; i++)
            {
                spaces += " ";
            }

            return spaces;
        }
    }
}
