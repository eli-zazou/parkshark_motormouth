package be.motormouth.allocation.services.mapper;

import be.motormouth.allocation.dto.AllocationDto;
import be.motormouth.allocation.entities.Allocation;
import be.motormouth.division.dto.DivisionDTO;
import be.motormouth.division.entities.Division;
import be.motormouth.division.services.DivisionMapper;
import be.motormouth.parkinglot.services.ParkingLotMapper;

import java.util.Collection;
import java.util.stream.Collectors;

public class AllocationMapper {
    public static Collection<AllocationDto> mapToDto(Collection<Allocation> allocations) {
        return allocations.stream().map(AllocationMapper::mapToDto).collect(Collectors.toList());
    }
    public static AllocationDto mapToDto(Allocation allocation) {
        return new AllocationDto(
                allocation.getId(),
                ParkingLotMapper.toDto(allocation.getParkingLot()),
                allocation.getMember(),
                allocation.getLicensePlate(),
                allocation.getStartTime(),
                allocation.getEndTime(),
                allocation.calculateDuration(),
                allocation.isActive()
        );
    }
}