package be.motormouth.exceptions;

public class UnmatchedLicensePlateException extends RuntimeException{
    public UnmatchedLicensePlateException(String message) {
        super(message);
    }
}
