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
    private LicensePlate licensePlate;
    private MembershipLevel membershipLevel;
    private String username;
    private String password;

    protected CreateMemberDto(){

    }

    public CreateMemberDto(String firstName, String lastName, String phoneNumber, String emailAddress, Address address, LicensePlate licensePlate, MembershipLevel membershipLevel, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.licensePlate = licensePlate;
        this.membershipLevel = membershipLevel;
        this.username = username;
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

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setMembershipLevel(MembershipLevel membershipLevel){
        this.membershipLevel = membershipLevel;
    }
}