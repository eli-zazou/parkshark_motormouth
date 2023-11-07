package be.motormouth.parkinglot.dtos;

import be.motormouth.member.entities.Address;

public record ContactPersonDto(Long id,
                               String phoneNumber,
                               String mobilePhoneNumber,
                               String email,
                               Address address) {
}
