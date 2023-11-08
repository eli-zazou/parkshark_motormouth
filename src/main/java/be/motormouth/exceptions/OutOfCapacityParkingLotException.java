package be.motormouth.exceptions;

public class OutOfCapacityParkingLotException extends RuntimeException {
    public OutOfCapacityParkingLotException(String message) {
        super(message);
    }
}
