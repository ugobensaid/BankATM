namespace BankATM
{
    public class Client
    {
        private string name;
        private string pinCode;
        private BankAccount bankAccount;

        public Client(string name, string pinCode, BankAccount bankAccount)
        {
            this.name = name;
            this.pinCode = pinCode;
            this.bankAccount = bankAccount;
        }

        public string getName()
        {
            return name;
        }

        public string getPinCode()
        {
            return pinCode;
        }

        public BankAccount getBankAccount()
        {
            return bankAccount;
        }
    }
}
