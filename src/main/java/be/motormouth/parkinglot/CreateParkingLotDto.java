package be.motormouth.parkinglot;

import be.motormouth.member.entities.Address;

public record CreateParkingLotDto(String name,
                                  Category category,
                                  int capacity,
                                  ContactPerson contactPerson,
                                  Address address,
                                  double pricePerHourInEuro,
                                  int numberOfPlacesAvailable) {
}
