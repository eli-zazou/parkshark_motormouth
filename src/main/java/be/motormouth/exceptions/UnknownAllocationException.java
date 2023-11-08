package be.motormouth.exceptions;

public class UnknownAllocationException extends RuntimeException {
    public UnknownAllocationException(String message) {
            super(message);
        }
}