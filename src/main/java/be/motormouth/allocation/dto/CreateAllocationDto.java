package be.motormouth.allocation.dto;

import be.motormouth.member.entities.Member;
import be.motormouth.parkinglot.ParkingLot;

import java.time.LocalDateTime;

public record CreateAllocationDto(ParkingLot parkingLot,
                                  Member member,
                                  String licensePlate,
                                  LocalDateTime startTime,
                                  LocalDateTime endTime,
                                  boolean isActive) {
}
