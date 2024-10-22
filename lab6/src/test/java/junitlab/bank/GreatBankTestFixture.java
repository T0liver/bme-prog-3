package junitlab.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junitlab.bank.impl.GreatSavingsBank;

public class GreatBankTestFixture {
    GreatSavingsBank bank;
    String acc1, acc2;

    @BeforeEach
    public void setUp() throws AccountNotExistsException {
        bank = new GreatSavingsBank();
        acc1 = bank.openAccount();
        acc2 =bank.openAccount();
        bank.deposit(acc1, 1500);
        bank.deposit(acc2, 12000);
    }

    @Test
    public void testTransfer() throws NotEnoughFundsException, AccountNotExistsException {
        bank.transfer(acc2, acc1, 3456);
        assertEquals(4956, bank.getBalance(acc1));
        assertEquals(8544, bank.getBalance(acc2));
    }

    @Test
    public void testTransferWithoutEnoughFunds() throws NotEnoughFundsException, AccountNotExistsException {
        assertThrows(NotEnoughFundsException.class, () -> bank.transfer(acc1, acc2, 3456));
    }

}
