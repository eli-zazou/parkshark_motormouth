package be.motormouth.exceptions;

public class UnknownParkingLotException extends RuntimeException {
    public UnknownParkingLotException(String message) {
        super(message);
    }
}
