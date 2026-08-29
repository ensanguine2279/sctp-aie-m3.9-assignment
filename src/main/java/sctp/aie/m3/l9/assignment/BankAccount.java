package sctp.aie.m3.l9.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankAccount {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccount.class);

    private static final int LARGE_AMOUNT_THRESHOLD = 10000;

    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }

        if (amount > LARGE_AMOUNT_THRESHOLD) {
            LOGGER.warn("Depositing a large amount: " + amount);
        }

        balance += amount;
        LOGGER.info("Deposited amount: " + amount + ", new balance: " + balance);
    }

    public void withdraw(double amount) throws InvalidAmountException, InsufficientAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }

        if (amount > LARGE_AMOUNT_THRESHOLD) {
            LOGGER.warn("Withdrawing a large amount: " + amount);
        }

        if (amount <= balance) {
            balance -= amount;
            LOGGER.info("Withdrew amount: " + amount + ", new balance: " + balance);
        } else {
            throw new InsufficientAmountException(balance, amount);
        }
    }

    private static void testDeposit(BankAccount account, double amount) {
        try {
            LOGGER.info("Current balance before deposit: " + account.getBalance());
            LOGGER.info("Attempting to deposit amount: " + amount);
            account.deposit(amount);
            LOGGER.info("Current balance after deposit: " + account.getBalance());
        } catch (InvalidAmountException ex) {
            LOGGER.error("An error occurred: ", ex);
        }
    }

    private static void testWithdraw(BankAccount account, double amount) {
        try {
            LOGGER.info("Current balance before withdrawal: " + account.getBalance());
            LOGGER.info("Attempting to withdraw amount: " + amount);
            account.withdraw(amount);
            LOGGER.info("Current balance after withdrawal: " + account.getBalance());
        } catch (InvalidAmountException | InsufficientAmountException ex) {
            LOGGER.error("An error occurred: ", ex);
        }
    }

    public static void runTest() {
        BankAccount account = new BankAccount(7000);
        LOGGER.info("Initial balance: " + account.getBalance());

        // Test depositing and withdrawing large amounts
        testDeposit(account, 17000);
        testWithdraw(account, 20000);

        // Test normal deposit and withdrawal
        testDeposit(account, 7000);
        testWithdraw(account, 2000);

        // Test depositing and withdrawing invalid amounts
        testDeposit(account, -77);
        testWithdraw(account, -22);
    }
}
