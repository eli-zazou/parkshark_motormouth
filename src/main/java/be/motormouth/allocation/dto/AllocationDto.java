package be.motormouth.allocation.dto;

import be.motormouth.member.entities.Member;
import be.motormouth.parkinglot.entities.ParkingLot;

import java.time.LocalDateTime;

public record AllocationDto(Long id,
                            ParkingLot parkingLot,
                            Member member,
                            String licensePlate,
                            LocalDateTime startTime,
                            LocalDateTime endTime,
                            boolean isActive) {
}
