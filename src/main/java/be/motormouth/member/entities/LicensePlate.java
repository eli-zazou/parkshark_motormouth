package be.motormouth.member.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LicensePlate {

    @Column(name = "license_plate_number")
    private String licensePlateNumber;
    @Column(name = "issuing_country" )
    private String issuingCountry;

    public java.lang.String getLicensePlateNumber() {
        return licensePlateNumber;
    }
}
