package be.motormouth.member.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

@Embeddable
public class Address {


    @Column
    private String streetName;
    @Column
    private String streetNumber; // in case we have 12A for example
    @Column
    private int postalCode;
    @Column
    private String postalLabel;

    public Address() {
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getPostalLabel() {
        return postalLabel;
    }
}
