package be.motormouth.member.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class LicensePlate {

    private String licensePlateNumber;
    private String issuingCountry;

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }
}
