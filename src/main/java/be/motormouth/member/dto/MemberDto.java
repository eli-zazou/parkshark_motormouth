package be.motormouth.member.dto;

import be.motormouth.member.entities.Address;
import be.motormouth.member.entities.LicensePlate;
import be.motormouth.member.entities.MembershipLevel;

import java.time.LocalDate;

public record MemberDto(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String emailAddress,
        Address address,
        String licensePlateNumber,
        LocalDate registrationDate
        , MembershipLevel membershipLevel) {}
