package junitlab.bank;

import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

public class BankParamTest {

    static Stream<org.junit.jupiter.params.provider.Arguments> downroundingData() {
        return Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(1100, 1100),
            org.junit.jupiter.params.provider.Arguments.of(1101, 1100),
            org.junit.jupiter.params.provider.Arguments.of(1149, 1100),
            org.junit.jupiter.params.provider.Arguments.of(1150, 1100),
            org.junit.jupiter.params.provider.Arguments.of(1151, 1100),
            org.junit.jupiter.params.provider.Arguments.of(1199, 1100),
            org.junit.jupiter.params.provider.Arguments.of(1200, 1200)
        );
    }
    
    static Stream<org.junit.jupiter.params.provider.Arguments> uproundingData() {
        return Stream.of(
            org.junit.jupiter.params.provider.Arguments.of(1100, 1100),
            org.junit.jupiter.params.provider.Arguments.of(1101, 1100),
            org.junit.jupiter.params.provider.Arguments.of(1149, 1100),
            org.junit.jupiter.params.provider.Arguments.of(1150, 1200),
            org.junit.jupiter.params.provider.Arguments.of(1151, 1200),
            org.junit.jupiter.params.provider.Arguments.of(1199, 1200),
            org.junit.jupiter.params.provider.Arguments.of(1200, 1200)
        );
    }

    @ParameterizedTest
    @MethodSource("downroundingData")
    public void testWithdrawRoundingFirstNationalBank(long amount, long rounded) throws Exception {
        FirstNationalBank bank = new FirstNationalBank();
        String accountNumber = bank.openAccount();
        bank.deposit(accountNumber, 10000);

        bank.withdraw(accountNumber, amount);
        long remainingBalance = bank.getBalance(accountNumber);

        assertEquals(10000 - rounded, remainingBalance);
    }

    @ParameterizedTest
    @MethodSource("uproundingData")
    public void testWithdrawRoundingGreatSavingsBank(long amount, long rounded) throws Exception {
        GreatSavingsBank bank = new GreatSavingsBank();
        String accountNumber = bank.openAccount();
        bank.deposit(accountNumber, 10000);

        bank.withdraw(accountNumber, amount);
        long remainingBalance = bank.getBalance(accountNumber);

        assertEquals(10000 - rounded, remainingBalance);
    }
}
