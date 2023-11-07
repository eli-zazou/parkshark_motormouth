package be.motormouth.exceptions;

public class UnknownDivisionException extends RuntimeException {
    public UnknownDivisionException(String errorMessage) {
        super(errorMessage);
    }
}
