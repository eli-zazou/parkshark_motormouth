package be.motormouth.parkinglot.entities;

import be.motormouth.division.entities.Division;
import be.motormouth.member.entities.Address;
import be.motormouth.parkinglot.Category;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


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
    @Cascade(CascadeType.PERSIST)
    private ContactPerson contactPerson;

    @ManyToOne
    @JoinColumn(name = "FK_DIVISION_ID")
    private Division division;

    @Embedded
    private Address address;

    @Column(name = "PRICE_PER_HOUR_IN_EURO")
    private double pricePerHourInEuro;

    @Column(name = "NUMBER_OF_PLACES_AVAILABLE")
    private int numberOfPlacesAvailable;

    public ParkingLot() {
    }

    public ParkingLot(String name, Category category, int capacity, ContactPerson contactPerson, Address address, double pricePerHourInEuro, int numberOfPlacesAvailable, Division division) {
        this.name = name;
        this.category = category;
        this.capacity = capacity;
        this.contactPerson = contactPerson;
        this.address = address;
        this.pricePerHourInEuro = pricePerHourInEuro;
        this.numberOfPlacesAvailable = numberOfPlacesAvailable;
        this.division = division;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getCapacity() {
        return capacity;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public Address getAddress() {
        return address;
    }

    public double getPricePerHourInEuro() {
        return pricePerHourInEuro;
    }

    public int getNumberOfPlacesAvailable() {
        return numberOfPlacesAvailable;
    }

    public Division getDivision() {
        return division;
    }

    public void setNumberOfPlacesAvailable(int numberOfPlacesAvailable) {
        this.numberOfPlacesAvailable = numberOfPlacesAvailable;
    }
}
