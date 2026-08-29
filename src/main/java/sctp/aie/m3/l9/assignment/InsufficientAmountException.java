package sctp.aie.m3.l9.assignment;

public class InsufficientAmountException extends Exception {
    public InsufficientAmountException(double balance, double amount) {
        super("Insufficient balance for withdrawal: " + amount + ", available balance: " + balance);
    }
}
