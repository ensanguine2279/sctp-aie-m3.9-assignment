package sctp.aie.m3.l9.assignment;

public class InvalidAmountException extends Exception {
    public InvalidAmountException(double amount) {
        super("Deposit or withdrawal amount is negative or zero: " + amount);
    }
}
