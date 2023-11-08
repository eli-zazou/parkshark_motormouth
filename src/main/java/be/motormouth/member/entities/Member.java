package be.motormouth.member.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    @SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
    private Long Id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email_address")
    private String emailAddress;

    @Embedded
    private Address address;
    @Embedded
    @Column
    private LicensePlate licencePlate;
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    @Column(name = "membership_level")
    @Enumerated(value = EnumType.STRING)
    private MembershipLevel membershipLevel;

    protected Member(){
        // for JPA
    }

    // todo make a builder.
    public Member(String firstName, String lastName, String phoneNumber,
                  String emailAddress, Address address , LicensePlate licensePlate,
                  MembershipLevel membershipLevel, LocalDate registrationDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.licencePlate = licensePlate;
        this.registrationDate = registrationDate;
        this.membershipLevel = membershipLevel;
    }

    public Address getAddress(){
        return this.address;
    }

    public Long getId() {
        return Id;
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

    public LicensePlate getLicencePlate() {
        return licencePlate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}
