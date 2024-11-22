package md.BankAccountTesting;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountHolderName;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountHolderName, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
        transactionHistory.add("Withdrawn: " + amount);
    }

    public double getBalance() {
        return balance;
    }

    public String getTransactionHistory() {
        return String.join("\n", transactionHistory);
    }
}
