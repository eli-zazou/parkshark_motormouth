package be.motormouth.member.dto;

import be.motormouth.member.entities.Address;
import be.motormouth.member.entities.LicensePlate;
import be.motormouth.member.entities.MembershipLevel;

import java.time.LocalDate;

public record CreateMemberDto (String firstName,
                               String lastName,
                               String phoneNumber,
                               String emailAddress,
                               Address address,
                               LicensePlate licensePlate,
                               MembershipLevel membershipLevel,
                               String password
) {
}
