package md.BankAccountTesting;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class BankAccountTest {

    private static BankAccount bankAccount;

    @BeforeAll
    static void setup() {
        System.out.println("Setting up resources for testing...");
        bankAccount = new BankAccount("John Doe", 1000.0);
    }

    @AfterAll
    static void teardown() {
        System.out.println("Cleaning up resources after testing...");
        bankAccount = null;
    }
    
    @BeforeEach
    void setupEach() {
        // Reset account before each test
        bankAccount = new BankAccount("John Doe", 1000.0);
    }

    @Test
    void testDeposit() {
        System.out.println("Running testDeposit...");
        bankAccount.deposit(500.0);
        assertEquals(1500.0, bankAccount.getBalance(), "Balance should be updated after deposit");
    }

    @Test
    void testWithdraw() {
        System.out.println("Running testWithdraw...");
        bankAccount.withdraw(300.0);
        assertEquals(700.0, bankAccount.getBalance(), "Balance should be updated after withdrawal");
    }

    @Test
    void testInsufficientFunds() {
        System.out.println("Running testInsufficientFunds...");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankAccount.withdraw(2000.0);
        });
        assertEquals("Insufficient balance", exception.getMessage());
    }

    @Test
    void testTransactionHistory() {
        System.out.println("Running testTransactionHistory...");
        bankAccount.deposit(200.0);
        bankAccount.withdraw(100.0);
        String history = bankAccount.getTransactionHistory();
        assertTrue(history.contains("Deposited: 200.0"), "Transaction history should contain deposit");
        assertTrue(history.contains("Withdrawn: 100.0"), "Transaction history should contain withdrawal");
    }
}
