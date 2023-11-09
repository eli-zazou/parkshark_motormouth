package be.motormouth.allocation.services;

import be.motormouth.allocation.AllocationPanacheRepository;
import be.motormouth.allocation.dto.CreateAllocationDto;
import be.motormouth.allocation.entities.Allocation;
import be.motormouth.exceptions.*;
import be.motormouth.member.entities.Member;
import be.motormouth.member.entities.MembershipLevel;
import be.motormouth.member.services.MemberService;
import be.motormouth.parkinglot.entities.ParkingLot;
import be.motormouth.parkinglot.services.ParkingLotService;
import be.motormouth.security.users.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.util.Collection;

@ApplicationScoped
@Transactional
public class AllocationService {
    private final AllocationPanacheRepository allocationPanacheRepository;
    private final ParkingLotService parkingLotService;
    private final MemberService memberService;
    private final Logger logger = Logger.getLogger(ParkingLotService.class);
    private String errorMessage;

    public AllocationService(AllocationPanacheRepository allocationPanacheRepository, ParkingLotService parkingLotService,
                             MemberService memberService) {
        this.allocationPanacheRepository = allocationPanacheRepository;
        this.parkingLotService = parkingLotService;
        this.memberService = memberService;
    }

    public Allocation startAllocation(CreateAllocationDto createAllocationDto, User connectedUser) {
        validateFilledIn(createAllocationDto);

        ParkingLot parkingLot = parkingLotService.getParkingLot(createAllocationDto.parkingLotId());
        Member member = connectedUser.getMember();

        validateLicensePlate(createAllocationDto.licensePlate(), member);
        decrementNumberOfPlacesAvailable(parkingLot);

        Allocation allocation = new Allocation(
                parkingLot,
                member,
                createAllocationDto.licensePlate()
        );

        return allocationPanacheRepository.createAllocation(allocation);
    }

    public Allocation stopAllocation(User connectedUser, String id) {
        Allocation allocation = allocationPanacheRepository.getAllocationById(Long.parseLong(id))
                .orElseThrow(() -> {
                    errorMessage = "Allocation " + id + " not known";
                    logger.info(errorMessage);
                    return new UnknownAllocationException(errorMessage);
                });

        validateMember(allocation, connectedUser.getMember());
        validateActiveAllocation(allocation);

        allocation.setEndTime(LocalDateTime.now());
        incrementNumberOfPlacesAvailable(allocation.getParkingLot());

        return allocation;
    }

    public Collection<Allocation> viewAllAllocations() {
        return allocationPanacheRepository.getAllAllocations();
    }

    private void validateFilledIn(CreateAllocationDto createAllocationDto) {
        if (createAllocationDto == null) {
            errorMessage = "Please provide an allocation";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (createAllocationDto.parkingLotId() == null) {
            errorMessage = "Please provide a parking lot id";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (createAllocationDto.licensePlate() == null) {
            errorMessage = "Please provide a license plate number";
            logger.info(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
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
            errorMessage = "This parking lot has no more places available";
            logger.info(errorMessage);
            throw new OutOfCapacityParkingLotException(errorMessage);
        }
        parkingLot.setNumberOfPlacesAvailable(parkingLot.getNumberOfPlacesAvailable() - 1);
    }

    private void validateMember(Allocation allocation, Member member) {
        if (!(allocation.getMember().equals(member))) {
            errorMessage = "Allocation " + allocation.getId() + " does not belong to " + member.getLastName();
            logger.info(errorMessage);
            throw new UnmatchedMemberException(errorMessage);
        }
    }

    private void validateActiveAllocation(Allocation allocation) {
        if (!allocation.isActive()) {
            errorMessage = "Allocation " + allocation.getId() + " is not active";
            logger.info(errorMessage);
            throw new InactiveAllocationException(errorMessage);
        }
    }

    private void incrementNumberOfPlacesAvailable(ParkingLot parkingLot) {
        parkingLot.setNumberOfPlacesAvailable(parkingLot.getNumberOfPlacesAvailable() + 1);
    }

    public Collection<Allocation> getAllocationsForMember(Long id) {
        memberService.getMemberById(id);
        return allocationPanacheRepository.getAllocationForMemberId(id);
    }

    public Collection<Allocation> getAllocationsForMember(Long id, boolean active) {
        memberService.getMemberById(id);
        if (active) {
            return allocationPanacheRepository.getActiveAllocationsForMemberId(id);
        }
        return allocationPanacheRepository.getStoppedAllocationsForMemberId(id);
    }
}
