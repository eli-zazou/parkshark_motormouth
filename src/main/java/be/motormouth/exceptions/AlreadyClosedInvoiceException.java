package be.motormouth.exceptions;

public class AlreadyClosedInvoiceException extends RuntimeException {
    public AlreadyClosedInvoiceException(String message) {
        super(message);
    }
}
