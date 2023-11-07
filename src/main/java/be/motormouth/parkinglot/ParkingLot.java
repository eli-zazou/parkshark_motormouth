package be.motormouth.parkinglot;

import be.motormouth.member.entities.Address;
import jakarta.persistence.*;


@Entity
@Table(name = "PARKING_LOT")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_lot_seq")
    @SequenceGenerator(name = "parking_lot_seq", sequenceName = "parking_lot_seq", allocationSize = 1)
    private Long id;

    private String name;

    private Category category;

    private int capacity;

    @OneToOne
    @JoinColumn(name = "FK_CONTACT_PERSON_ID")
    private ContactPerson contactPerson;

    @Embedded
    private Address address;

    @Column(name = "PRICE_PER_HOUR_IN_EURO")
    private double pricePerHourInEuro;

    @Column(name = "NUMBER_OF_PLACES_AVAILABLE")
    private int numberOfPlacesAvailable;

    public int getNumberOfPlacesAvailable() {
        return numberOfPlacesAvailable;
    }

    public void setNumberOfPlacesAvailable(int numberOfPlacesAvailable) {
        this.numberOfPlacesAvailable = numberOfPlacesAvailable;
    }
}
