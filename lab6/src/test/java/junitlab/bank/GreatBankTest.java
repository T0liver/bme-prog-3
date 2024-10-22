package junitlab.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import junitlab.bank.impl.GreatSavingsBank;

public class GreatBankTest {

    private GreatSavingsBank bank;

    @BeforeEach
    public void setUp() {
        bank = new GreatSavingsBank();
    }

    @Test
    public void testOpenAccount() throws AccountNotExistsException {
        String acc = bank.openAccount();
        assertEquals(0, bank.getBalance(acc));
    }

    @Test
    public void testUniqueAccount() {
        String acc1 = bank.openAccount();
        String acc2 = bank.openAccount();
        assertNotEquals(acc1, acc2);
    }

    @Test
    public void testInvalidAccount() throws AccountNotExistsException {
        assertThrows(AccountNotExistsException.class, () -> bank.getBalance("EZNEMLETEZIK"));
    }

    @Test
    public void testDeposit() throws AccountNotExistsException {
        String acc = bank.openAccount();
        bank.deposit(acc, 2000);
        assertEquals(2000, bank.getBalance(acc));
    }

}
