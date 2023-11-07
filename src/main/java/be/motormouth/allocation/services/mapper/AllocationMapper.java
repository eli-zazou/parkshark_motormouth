package be.motormouth.allocation.services.mapper;

import be.motormouth.allocation.dto.AllocationDto;
import be.motormouth.allocation.entities.Allocation;

public class AllocationMapper {
    public static AllocationDto mapToDto(Allocation allocation) {
        return new AllocationDto(
                allocation.getId(),
                allocation.getParkingLot(),
                allocation.getMember(),
                allocation.getLicensePlate(),
                allocation.getStartTime(),
                allocation.getEndTime(),
                allocation.isActive()
        );
    }
}