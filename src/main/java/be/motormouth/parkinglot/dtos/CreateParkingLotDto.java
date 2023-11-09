package be.motormouth.parkinglot.dtos;

import be.motormouth.member.entities.Address;
import be.motormouth.parkinglot.Category;
import be.motormouth.parkinglot.entities.ContactPerson;

public record CreateParkingLotDto(String name,
                                  Category category,
                                  int capacity,
                                  CreateContactPersonDto createContactPersonDto,
                                  Address address,
                                  double pricePerHourInEuro) {
    @Override
    public String toString() {
        return "CreateParkingLotDto{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", capacity=" + capacity +
                ", createContactPersonDto=" + createContactPersonDto +
                ", address=" + address +
                ", pricePerHourInEuro=" + pricePerHourInEuro +
                '}';
    }
}
