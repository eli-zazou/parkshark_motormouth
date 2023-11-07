package be.motormouth.member.dto;

import be.motormouth.member.entities.Address;
import be.motormouth.member.entities.LicensePlate;
import be.motormouth.member.entities.MembershipLevel;

import java.time.LocalDate;

public class CreateMemberDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private Address address;
    private LicensePlate licencePlate;
    private LocalDate registrationDate;
    private MembershipLevel membershipLevel;
    private String userid;
    private String password;

    public CreateMemberDto(String firstName, String lastName, String phoneNumber, String emailAddress, Address address, LicensePlate licencePlate, LocalDate registrationDate, MembershipLevel membershipLevel, String userid, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.licencePlate = licencePlate;
        this.registrationDate = registrationDate;
        this.membershipLevel = membershipLevel;
        this.userid = userid;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public LicensePlate getLicencePlate() {
        return licencePlate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }
}