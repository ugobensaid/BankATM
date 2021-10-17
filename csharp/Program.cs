using System;

namespace BankATM
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;

            Console.Write("Please enter your name : ");
            string name = Console.ReadLine();

            ATMActions.pinCodeCreation(name);
        }
    }
}
