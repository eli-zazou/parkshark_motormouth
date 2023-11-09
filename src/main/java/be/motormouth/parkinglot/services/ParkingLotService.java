package be.motormouth.parkinglot.services;

import be.motormouth.division.entities.Division;
import be.motormouth.division.services.DivisionService;
import be.motormouth.exceptions.UnknownParkingLotException;
import be.motormouth.parkinglot.ContactPersonPanacheRepository;
import be.motormouth.parkinglot.ParkingLotPanacheRepository;
import be.motormouth.parkinglot.dtos.CreateParkingLotDto;
import be.motormouth.parkinglot.entities.ParkingLot;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.regex.Pattern;

@ApplicationScoped
@Transactional
public class ParkingLotService {
    private final org.jboss.logging.Logger logger = Logger.getLogger(ParkingLotService.class);
    private final ParkingLotPanacheRepository parkingLotPanacheRepository;
    private final DivisionService divisionService;
    @Inject
    ContactPersonPanacheRepository contactPersonRepository;
    private final Logger logger = Logger.getLogger(ParkingLotService.class);
    private String errorMessage;

    public ParkingLotService(ParkingLotPanacheRepository parkingLotPanacheRepository, DivisionService divisionService) {
        this.parkingLotPanacheRepository = parkingLotPanacheRepository;
        this.divisionService = divisionService;
    }

    @Transactional
    public ParkingLot createParkingLot(CreateParkingLotDto createParkingLotDto, String divisionId) {
        if (divisionId == null)
            throw new IllegalArgumentException("division id must not be null");
        Division division = divisionService.viewDivisionsById(divisionId);
        validateInput(createParkingLotDto);
        return parkingLotPanacheRepository.createParkingLot(ParkingLotMapper.toEntity(createParkingLotDto, division));
    }

    public List<ParkingLot> getAllParkingLots() {
        return parkingLotPanacheRepository.getAllParkingLots();
    }
    public ParkingLot getParkingLot(Long id) {
        return parkingLotPanacheRepository.findByIdOptional(id)
                .orElseThrow(() -> {
                    errorMessage = "Parking lot " + id.toString() + " not found";
                    logger.info(errorMessage);
                    return new UnknownParkingLotException(errorMessage);
                });
    }

    private void validateInput(CreateParkingLotDto createParkingLotDto) {
        ParkingLot parkingLotByName = parkingLotPanacheRepository.getParkingLotByName(createParkingLotDto.name());
        if (parkingLotByName != null)
            throw new IllegalArgumentException("The name of the parking lot already exists. Choose another name.");

        if(createParkingLotDto.createContactPersonDto() == null)
            throw new IllegalArgumentException("Contact person is required");

        if (createParkingLotDto.createContactPersonDto().email() == null || createParkingLotDto.createContactPersonDto().email().isEmpty())
            throw new IllegalArgumentException("Contact person's email is required");

        validateEmail(createParkingLotDto);

        if ((createParkingLotDto.createContactPersonDto().phoneNumber() == null || createParkingLotDto.createContactPersonDto().phoneNumber().isEmpty()) &&
                (createParkingLotDto.createContactPersonDto().mobilePhoneNumber() == null || createParkingLotDto.createContactPersonDto().mobilePhoneNumber().isEmpty()))
            throw new IllegalArgumentException("At least one phone number is required");
    }

    private static void validateEmail(CreateParkingLotDto createParkingLotDto) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (!Pattern.compile(regex).matcher(createParkingLotDto.createContactPersonDto().email()).matches())
            throw new IllegalArgumentException("Contact person's email \"" + createParkingLotDto.createContactPersonDto().email()  + "\" is not valid. Follow those rules : \n" +
                    """
                            For the local part : \n
                            Numeric values allowed from 0 to 9.\n
                            Both uppercase and lowercase letters from a to z are allowed.\n
                            Allowed are underscore “_”, hyphen “-“, and dot “.”\n
                            Dot isn’t allowed at the start and end of the local part.\n
                            Consecutive dots aren’t allowed.\n
                            For the local part, a maximum of 64 characters are allowed.\n
                            
                            For the domain part : \n
                            Numeric values allowed from 0 to 9.\n
                            We allow both uppercase and lowercase letters from a to z.\n
                            Hyphen “-” and dot “.” aren’t allowed at the start and end of the domain part.\n
                            No consecutive dots.\n                        
                            """);
    }
}
