using Microsoft.VisualStudio.TestTools.UnitTesting;
using BankATM;

namespace BankATMTests
{

    [TestClass]
    public class BankAccountTests
    {

        [TestMethod]
        public void withdrawWithSuperiorAmountThanAvailableTest()
        {
            // Arrange
            int withdrawAmount = 70;
            bool expected = false;
            BankAccount account = new BankAccount(50);

            // Act
            bool actual = account.withdraw(withdrawAmount);

            // Assert
            Assert.AreEqual(expected, actual);
        }

        [TestMethod]
        public void withdrawWithValidAmountTest()
        {
            // Arrange
            int withdrawAmount = 30;
            bool expected = true;
            BankAccount account = new BankAccount(50);

            // Act
            bool actual = account.withdraw(withdrawAmount);

            // Assert
            Assert.AreEqual(expected, actual);
        }

        [TestMethod]
        public void withdrawWithNonMultipleOfTenAmountTest()
        {
            // Arrange
            int withdrawAmount = 25;
            bool expected = false;
            BankAccount account = new BankAccount(50);

            // Act
            bool actual = account.withdraw(withdrawAmount);

            // Assert
            Assert.AreEqual(expected, actual);
        }

        [TestMethod]
        public void depositWithNonMultipleOfTenAmountTest()
        {
            // Arrange
            int depositAmount = 25;
            bool expected = false;
            BankAccount account = new BankAccount(0);

            // Act
            bool actual = account.deposit(depositAmount);

            // Assert
            Assert.AreEqual(expected, actual);
        }

        [TestMethod]
        public void depositWithValidAmountTest()
        {
            // Arrange
            int depositAmount = 10;
            bool expected = true;
            BankAccount account = new BankAccount(0);

            // Act
            bool actual = account.deposit(depositAmount);

            // Assert
            Assert.AreEqual(expected, actual);
        }
    }
}
