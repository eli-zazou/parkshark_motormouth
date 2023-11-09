package be.motormouth.testConstants;

import be.motormouth.division.entities.Division;
import be.motormouth.member.entities.Address;
import be.motormouth.parkinglot.Category;
import be.motormouth.parkinglot.dtos.CreateContactPersonDto;
import be.motormouth.parkinglot.dtos.CreateParkingLotDto;
import be.motormouth.parkinglot.entities.ContactPerson;
import be.motormouth.parkinglot.entities.ParkingLot;
import be.motormouth.parkinglot.services.ParkingLotMapper;

public class ParkingLotTestConstants {
    public static final CreateParkingLotDto CREATE_PARKING_LOT_DTO = new CreateParkingLotDto("parking lot a", Category.UNDERGROUND_BUILDING, 500, new CreateContactPersonDto("023456622", "0469456878", "mmm@mmm.mmm", new Address("Contact test street", "66", 1180, "Uccle")), new Address("Parking lot test street", "77", 1000, "Brussels"), 2);

    public static final Division DIVISION = new Division(1, "division a", "original name", "director name", null);

    public static final ParkingLot PARKING_LOT = new ParkingLot(1, "parking lot a", Category.UNDERGROUND_BUILDING, 500, new ContactPerson("023456589", "0465789312", "lll@mmm.ddd", new Address("contact street test", "65", 1180, "Uccle")), DIVISION, new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);

    public static final Division DIVISION_1 = new Division("division a", "original name", "director name", null);

    public static final ParkingLot PARKING_LOT_1 = ParkingLotMapper.toEntity(CREATE_PARKING_LOT_DTO, DIVISION_1);
}
