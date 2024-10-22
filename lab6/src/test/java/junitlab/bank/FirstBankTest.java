package junitlab.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import junitlab.bank.impl.FirstNationalBank;

public class FirstBankTest {

    private FirstNationalBank bank;

    @BeforeEach
    public void setUp() {
        bank = new FirstNationalBank();
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
        assertEquals(-1L, bank.getBalance("EZNEMLETEZIK"));
    }

    @Test
    public void testDeposit() throws AccountNotExistsException {
        String acc = bank.openAccount();
        bank.deposit(acc, 2000);
        Assertions.assertEquals(2000, bank.getBalance(acc));
    }

}
