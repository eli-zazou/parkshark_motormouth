package be.motormouth.parkinglot.entities;

import be.motormouth.member.entities.Address;
import jakarta.persistence.*;

@Entity
@Table(name = "CONTACT_PERSON")
public class ContactPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_person_seq")
    @SequenceGenerator(name = "contact_person_seq", sequenceName = "contact_person_seq", allocationSize = 1)
    private Long id;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "MOBILE_PHONE_NUMBER")
    private String mobilePhoneNumber;

    private String email;

    @Embedded
    private Address address;

    public ContactPerson() {
    }

    public ContactPerson(String phoneNumber,
                         String mobilePhoneNumber,
                         String email,
                         Address address) {
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
}