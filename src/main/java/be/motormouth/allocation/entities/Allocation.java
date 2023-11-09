package be.motormouth.allocation.entities;

import be.motormouth.member.entities.Member;
import be.motormouth.parkinglot.entities.ParkingLot;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ALLOCATION")
public class Allocation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allocation_seq")
    @SequenceGenerator(name = "allocation_seq", sequenceName = "allocation_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "FK_PARKING_LOT_ID")
    private ParkingLot parkingLot;
    @ManyToOne
    @JoinColumn(name = "FK_MEMBER_ID")
    private Member member;
    private String licensePlate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Allocation(ParkingLot parkingLot, Member member, String licensePlate) {
        this.parkingLot = parkingLot;
        this.member = member;
        this.licensePlate = licensePlate;
        this.startTime = LocalDateTime.now();
    }

    protected Allocation() {
        // for JPA
    }

    public Long getId() {
        return id;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public Member getMember() {
        return member;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isActive() {
        return endTime == null;
    }
}
