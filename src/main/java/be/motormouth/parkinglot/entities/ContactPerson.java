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

    protected ContactPerson() {
    }

    public ContactPerson(String phoneNumber,
                         String mobilePhoneNumber,
                         String email,
                         Address address) {
        this.phoneNumber = phoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email = email;
        this.address = address;
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

//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public void setMobilePhoneNumber(String mobilePhoneNumber) {
//        this.mobilePhoneNumber = mobilePhoneNumber;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
}
