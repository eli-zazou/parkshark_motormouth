package be.motormouth.allocation.dto;

import be.motormouth.member.entities.Member;
import be.motormouth.parkinglot.dtos.ParkingLotDto;
import be.motormouth.parkinglot.entities.ParkingLot;

import java.time.LocalDateTime;

public record AllocationDto(Long id,
                            ParkingLotDto parkingLotDto,
                            Member member,
                            String licensePlate,
                            LocalDateTime startTime,
                            LocalDateTime endTime,
                            String duration,
                            boolean isActive) {
}
