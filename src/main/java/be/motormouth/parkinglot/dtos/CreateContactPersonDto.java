package be.motormouth.parkinglot.dtos;

import be.motormouth.member.entities.Address;

public record CreateContactPersonDto(String phoneNumber,
                                     String mobilePhoneNumber,
                                     String email,
                                     Address address) {
}
