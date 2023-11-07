package be.motormouth.allocation.services;

import be.motormouth.allocation.AllocationPanacheRepository;
import be.motormouth.allocation.dto.CreateAllocationDto;
import be.motormouth.allocation.entities.Allocation;
import be.motormouth.exceptions.OutOfCapacityParkingLotException;
import be.motormouth.exceptions.UnmatchedLicensePlateException;
import be.motormouth.member.entities.Member;
import be.motormouth.member.entities.MembershipLevel;
import be.motormouth.parkinglot.ParkingLot;
import be.motormouth.parkinglot.ParkingLotService;
import be.motormouth.security.users.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

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

    public Allocation addAllocation(CreateAllocationDto createAllocationDto, User connectedUser) {
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
}
