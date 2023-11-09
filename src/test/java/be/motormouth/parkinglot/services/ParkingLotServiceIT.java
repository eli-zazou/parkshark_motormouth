package be.motormouth.parkinglot.services;

import be.motormouth.member.entities.Address;
import be.motormouth.parkinglot.Category;
import be.motormouth.parkinglot.ParkingLotPanacheRepository;
import be.motormouth.parkinglot.dtos.CreateContactPersonDto;
import be.motormouth.parkinglot.dtos.CreateParkingLotDto;
import be.motormouth.parkinglot.entities.ParkingLot;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static be.motormouth.testConstants.ParkingLotTestConstants.*;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class ParkingLotServiceIT {
    @Inject
    ParkingLotService parkingLotService;
    @Inject
    ParkingLotPanacheRepository parkingLotPanacheRepository;
    @Test
    void createParkingLot(){
        //create new parking lot then get it by his name from the db to check if present
        CreateParkingLotDto parkingLotZDto = new CreateParkingLotDto("parking lot Z", Category.UNDERGROUND_BUILDING, 500, new CreateContactPersonDto("023456622", "0469456878", "mmm@mmm.mmm", new Address("Contact test street", "66", 1180, "Uccle")), new Address("Parking lot test street", "77", 1000, "Brussels"), 2);
        ParkingLot parkingLotZEntity = parkingLotService.createParkingLot(parkingLotZDto, String.valueOf(DIVISION.getId()));
        ParkingLot parkingLotZEntityFromDb = parkingLotPanacheRepository.getParkingLotByName(parkingLotZDto.name());
        Assertions.assertEquals(parkingLotZEntityFromDb.getName(), parkingLotZEntity.getName());
    }
}
