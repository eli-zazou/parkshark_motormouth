package be.motormouth.parkinglot;

import be.motormouth.member.entities.Address;

public record ParkingLotDto(Long id,
                            String name,
                            Category category,
                            int capacity,
                            ContactPerson contactPerson,
                            Address address,
                            double pricePerHourInEuro,
                            int numberOfPlacesAvailable) {
}
