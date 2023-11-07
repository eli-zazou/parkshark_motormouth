package be.motormouth.parkinglot.dtos;

import be.motormouth.member.entities.Address;
import be.motormouth.parkinglot.Category;
import be.motormouth.parkinglot.entities.ContactPerson;

public record ParkingLotDto(Long id,
                            String name,
                            Category category,
                            int capacity,
                            ContactPerson contactPerson,
                            Address address,
                            double pricePerHourInEuro,
                            int numberOfPlacesAvailable) {
}
