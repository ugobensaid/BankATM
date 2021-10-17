using System;

namespace BankATM
{
    public class BankAccount
    {
        private int availableAmount;

        public BankAccount(int availableAmount)
        {
            this.availableAmount = availableAmount;
        }

        public bool deposit(int amount)
        {
            if (amount % 10 == 0 && amount > 0)
            {
                availableAmount += amount;
                Console.WriteLine("{0}€ added to your account.", amount);
                return true;
            }
            else
            {
                Console.WriteLine("You only can deposit an amount of a multiple of 10");
                return false;
            }
        }

        public bool withdraw(int amount)
        {
            if (amount % 10 != 0 || amount <= 0)
            {
                Console.WriteLine("You only can withdraw an amount of a multiple of 10");
                return false;
            }
            else if (availableAmount >= amount)
            {
                availableAmount -= amount;
                Console.WriteLine("{0} € withdraw from your account.", amount);
                return true;
            }
            else
            {
                Console.WriteLine("You don't have enough money");
                return false;
            }
        }

        public int getAvailableAmount()
        {
            return availableAmount;
        }
    }
}
