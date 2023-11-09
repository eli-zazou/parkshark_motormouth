package be.motormouth.allocation.services.mapper;

import be.motormouth.allocation.dto.AllocationDto;
import be.motormouth.allocation.entities.Allocation;
import be.motormouth.division.dto.DivisionDTO;
import be.motormouth.division.entities.Division;
import be.motormouth.division.services.DivisionMapper;
import be.motormouth.parkinglot.services.ParkingLotMapper;

import java.time.Duration;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
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
                convert(allocation.calculateDuration()),
                allocation.isActive()
        );
    }
    private static String convert(Duration duration) {
        long millis = duration.toMillis();
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }
}