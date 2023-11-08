package be.motormouth.allocation.services.mapper;

import be.motormouth.allocation.dto.AllocationDto;
import be.motormouth.allocation.entities.Allocation;
import be.motormouth.division.dto.DivisionDTO;
import be.motormouth.division.entities.Division;
import be.motormouth.division.services.DivisionMapper;

import java.util.Collection;
import java.util.stream.Collectors;

public class AllocationMapper {
    public static Collection<AllocationDto> mapToDto(Collection<Allocation> allocations) {
        return allocations.stream().map(AllocationMapper::mapToDto).collect(Collectors.toList());
    }
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