package be.motormouth.allocation.services;

import be.motormouth.allocation.AllocationPanacheRepository;
import be.motormouth.allocation.dto.CreateAllocationDto;
import be.motormouth.allocation.entities.Allocation;
import be.motormouth.parkinglot.ParkingLotService;
import be.motormouth.security.users.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AllocationService {
    private final AllocationPanacheRepository allocationPanacheRepository;
    private final ParkingLotService parkingLotService;

    public AllocationService(AllocationPanacheRepository allocationPanacheRepository, ParkingLotService parkingLotService) {
        this.allocationPanacheRepository = allocationPanacheRepository;
        this.parkingLotService = parkingLotService;
    }

    public Allocation addAllocation(CreateAllocationDto createAllocationDto, User connectedUser) {
        Allocation allocation = new Allocation(
                parkingLotService.getParkingLot(createAllocationDto.parkingLotId()),
                connectedUser.getMember(),
                createAllocationDto.licensePlate()
        );
        allocationPanacheRepository.persist(allocation);
        return allocation;
    }
}
