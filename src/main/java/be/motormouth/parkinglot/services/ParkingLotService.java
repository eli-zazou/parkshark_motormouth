package be.motormouth.parkinglot.services;

import be.motormouth.division.entities.Division;
import be.motormouth.division.services.DivisionService;
import be.motormouth.parkinglot.ContactPersonPanacheRepository;
import be.motormouth.parkinglot.ParkingLotPanacheRepository;
import be.motormouth.parkinglot.dtos.CreateParkingLotDto;
import be.motormouth.parkinglot.entities.ParkingLot;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ParkingLotService {
    private final org.jboss.logging.Logger logger = Logger.getLogger(ParkingLotService.class);
    @Inject
    ParkingLotPanacheRepository parkingLotPanacheRepository;
    @Inject
    DivisionService divisionService;
    @Inject
    ContactPersonPanacheRepository contactPersonRepository;


    @Transactional
    public ParkingLot createParkingLot(CreateParkingLotDto createParkingLotDto, String divisionId) {
        logger.info(createParkingLotDto);
        //check if id is null and if division exist
        if(divisionId == null)
            throw new IllegalArgumentException("division id must not be null");

        Division division = divisionService.viewDivisionsById(divisionId);

        //contactPersonRepository.createContactPerson(ContactPersonMapper.toEntity(createParkingLotDto.createContactPersonDto()));

        return parkingLotPanacheRepository.createParkingLot(ParkingLotMapper.toEntity(createParkingLotDto, division));
    }
}
