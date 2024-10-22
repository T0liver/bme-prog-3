package junitlab.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import junitlab.bank.impl.GreatSavingsBank;

class GreatBankFullTest {

    GreatSavingsBank bank;

    @BeforeEach
    public void setUp() {
        bank = new GreatSavingsBank();
    }

    @Test
    public void testOpenAccount() {
        String accountNumber = bank.openAccount();
        assertNotNull(accountNumber);
        assertTrue(accountNumber.startsWith("47328000-"));
    }

    @Test
    public void testCloseAccount_AccountWithZeroBalance() throws AccountNotExistsException {
        String accountNumber = bank.openAccount();
        assertTrue(bank.closeAccount(accountNumber));
    }

    @Test
    public void testCloseAccount_AccountWithNonZeroBalance() throws AccountNotExistsException {
        String accountNumber = bank.openAccount();
        bank.deposit(accountNumber, 1000);
        assertFalse(bank.closeAccount(accountNumber));
    }

    @Test
    public void testDeposit_ValidAmount() throws AccountNotExistsException {
        String accountNumber = bank.openAccount();
        bank.deposit(accountNumber, 1234);
        assertEquals(1200, bank.getBalance(accountNumber));
    }

    @Test
    public void testWithdraw_SufficientBalance() throws AccountNotExistsException, NotEnoughFundsException {
        String accountNumber = bank.openAccount();
        bank.deposit(accountNumber, 2000);
        bank.withdraw(accountNumber, 1500);
        assertEquals(500, bank.getBalance(accountNumber));
    }

    @Test
    public void testWithdraw_InsufficientFunds() throws AccountNotExistsException {
        String accountNumber = bank.openAccount();
        bank.deposit(accountNumber, 1000);
        assertThrows(NotEnoughFundsException.class, () -> bank.withdraw(accountNumber, 2000));
    }

    @Test
    public void testGetBalance() throws AccountNotExistsException {
        String accountNumber = bank.openAccount();
        assertEquals(0, bank.getBalance(accountNumber));
        bank.deposit(accountNumber, 500);
        assertEquals(500, bank.getBalance(accountNumber));
    }

    @Test
    public void testTransfer_SufficientFunds() throws AccountNotExistsException, NotEnoughFundsException {
        String sourceAccount = bank.openAccount();
        String targetAccount = bank.openAccount();
        bank.deposit(sourceAccount, 2000);
        bank.transfer(sourceAccount, targetAccount, 1000);
        assertEquals(1000, bank.getBalance(sourceAccount));
        assertEquals(1000, bank.getBalance(targetAccount));
    }

    @Test
    public void testTransfer_InsufficientFunds() throws AccountNotExistsException {
        String sourceAccount = bank.openAccount();
        String targetAccount = bank.openAccount();
        bank.deposit(sourceAccount, 1000);
        assertThrows(NotEnoughFundsException.class, () -> bank.transfer(sourceAccount, targetAccount, 2000));
    }

    @Test
    public void testTransfer_InvalidAmount() throws AccountNotExistsException {
        String sourceAccount = bank.openAccount();
        String targetAccount = bank.openAccount();
        assertThrows(IllegalArgumentException.class, () -> bank.transfer(sourceAccount, targetAccount, -100));
    }

    @Test
    public void testGetAccount_AccountDoesNotExist() {
        assertThrows(AccountNotExistsException.class, () -> bank.getBalance("nonexistent"));
    }
}
