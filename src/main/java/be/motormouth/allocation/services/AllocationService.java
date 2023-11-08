package be.motormouth.allocation.services;

import be.motormouth.allocation.AllocationPanacheRepository;
import be.motormouth.allocation.dto.CreateAllocationDto;
import be.motormouth.allocation.entities.Allocation;
import be.motormouth.exceptions.OutOfCapacityParkingLotException;
import be.motormouth.exceptions.UnknownAllocationException;
import be.motormouth.exceptions.UnmatchedLicensePlateException;
import be.motormouth.exceptions.UnmatchedMemberException;
import be.motormouth.member.entities.Member;
import be.motormouth.member.entities.MembershipLevel;
import be.motormouth.parkinglot.entities.ParkingLot;
import be.motormouth.parkinglot.services.ParkingLotService;
import be.motormouth.security.users.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class AllocationService {
    private final AllocationPanacheRepository allocationPanacheRepository;
    private final ParkingLotService parkingLotService;
    private final Logger logger = Logger.getLogger(ParkingLotService.class);
    private String errorMessage;

    public AllocationService(AllocationPanacheRepository allocationPanacheRepository, ParkingLotService parkingLotService) {
        this.allocationPanacheRepository = allocationPanacheRepository;
        this.parkingLotService = parkingLotService;
    }

    public Allocation startAllocation(CreateAllocationDto createAllocationDto, User connectedUser) {
        ParkingLot parkingLot = parkingLotService.getParkingLot(createAllocationDto.parkingLotId());
        Member member = connectedUser.getMember();

        validateLicensePlate(createAllocationDto.licensePlate(), member);
        decrementNumberOfPlacesAvailable(parkingLot);

        Allocation allocation = new Allocation(
                parkingLot,
                member,
                createAllocationDto.licensePlate()
        );
        allocationPanacheRepository.persist(allocation);
        return allocation;
    }
    public Allocation stopAllocation(User connectedUser, String id) {
        Member member = connectedUser.getMember();

        Optional<Allocation> foundAllocation = allocationPanacheRepository.findByIdOptional(Long.parseLong(id));
        if (foundAllocation.isEmpty()) {
            errorMessage = "Allocation " + id + " not known";
            logger.errorf(errorMessage);
            throw new UnknownAllocationException(errorMessage);
        }
        Allocation allocation = foundAllocation.get();
        if (!(allocation.getMember().equals(member))) {
            errorMessage = "Allocation " + id + " does not belong to " + member.getLastName();
            logger.errorf(errorMessage);
            throw new UnmatchedMemberException(errorMessage);
        }
        if (!allocation.isActive()) {
            errorMessage = "Allocation " + id + " is not active";
            logger.errorf(errorMessage);
            throw new UnknownAllocationException(errorMessage); //todo - other exception
        }
        allocation.setEndTime(LocalDateTime.now());
        return allocation;
    }

    private void validateLicensePlate(String licensePlate, Member member) {
        if (member.getMembershipLevel() != MembershipLevel.GOLD
                && !licensePlate.equalsIgnoreCase(member.getLicencePlate().getLicensePlateNumber())) {
            errorMessage = String.format("This license plate number %s does not match the members licence plate number %s." +
                    "Only gold members may use a different license plate number.", licensePlate, member.getLicencePlate().getLicensePlateNumber());
            logger.info(errorMessage);
            throw new UnmatchedLicensePlateException(errorMessage);
        }
    }

    private void decrementNumberOfPlacesAvailable(ParkingLot parkingLot) {
        if (parkingLot.getNumberOfPlacesAvailable() <= 0) {
            errorMessage = "This parking lot is out of capacity";
            logger.info(errorMessage);
            throw new OutOfCapacityParkingLotException(errorMessage);
        }
        parkingLot.setNumberOfPlacesAvailable(parkingLot.getNumberOfPlacesAvailable() - 1);
    }

    public Collection<Allocation> viewAllAllocations() {
        return allocationPanacheRepository.listAll();
    }
}
