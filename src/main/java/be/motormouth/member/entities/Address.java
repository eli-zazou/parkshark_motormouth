package be.motormouth.member.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String streetName;
    private String streetNumber; // in case we have 12A for example
    private int postalCode;
    private String postalLabel;
}
