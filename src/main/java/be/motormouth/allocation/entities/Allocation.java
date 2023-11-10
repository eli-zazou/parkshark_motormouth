package be.motormouth.allocation.entities;

import be.motormouth.member.entities.Member;
import be.motormouth.parkinglot.entities.ParkingLot;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AllocationStatus allocationStatus;

    public Allocation(ParkingLot parkingLot, Member member, String licensePlate) {
        this.parkingLot = parkingLot;
        this.member = member;
        this.licensePlate = licensePlate;
        this.startTime = LocalDateTime.now();
        this.allocationStatus = AllocationStatus.NOT_YET_INVOICED;
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

    public AllocationStatus getAllocationStatus() {
        return allocationStatus;
    }

    public Duration calculateDuration() {
        Duration duration;
        if (endTime == null ) {
            return duration = Duration.between(startTime, LocalDateTime.now());
        }
        else {
            return duration = Duration.between(startTime, endTime);
        }
//        long millis = duration.toMillis();
//        return String.format("%02d:%02d:%02d",
//                TimeUnit.MILLISECONDS.toHours(millis),
//                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
//                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public boolean isActive() {
        return endTime == null;
    }

    public void setAllocationStatus(AllocationStatus allocationStatus) {
        this.allocationStatus = allocationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Allocation that = (Allocation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
