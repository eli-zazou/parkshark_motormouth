package be.motormouth.member.dto;

import be.motormouth.member.entities.Address;
import be.motormouth.member.entities.MembershipLevel;

import java.time.LocalDate;



public record MemberDtoSpecificFields(
        Long id,
        String firstName,
        String lastName,
        String licensePlateNumber,
        String phoneNumber,
        String emailAddress,
        LocalDate registrationDate
       ) {}