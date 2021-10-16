namespace BankATM
{
    using System;

    public class Client
    {
        private string name;
        private string pinCode;
        private BankAccount bankAccount;

        Client(string name, string pinCode, BankAccount bankAccount)
        {
            this.name = name;
            this.pinCode = pinCode;
            this.bankAccount = bankAccount;
        }

        string getName()
        {
            return name;
        }

        string getPinCode()
        {
            return pinCode;
        }

        BankAccount getBankAccount()
        {
            return bankAccount;
        }
    }
}
