package be.motormouth.parkinglot.dtos;

public record ListParkingLotDto (Long id,
                                 String name,
                                 int capacity,
                                 String contactPersonEmail,
                                 String contactPersonPhonenumber) {
}
