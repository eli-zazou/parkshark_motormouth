package be.motormouth.parkinglot.services;

import be.motormouth.division.DivisionPanacheRepository;
import be.motormouth.division.entities.Division;
import be.motormouth.division.services.DivisionService;
import be.motormouth.member.entities.Address;
import be.motormouth.parkinglot.Category;
import be.motormouth.parkinglot.ParkingLotPanacheRepository;
import be.motormouth.parkinglot.dtos.CreateContactPersonDto;
import be.motormouth.parkinglot.dtos.CreateParkingLotDto;
import be.motormouth.parkinglot.dtos.ParkingLotDto;
import be.motormouth.parkinglot.entities.ContactPerson;
import be.motormouth.parkinglot.entities.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static be.motormouth.ParkingLotConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ParkingLotServiceTest {
    private DivisionService divisionService;
    private DivisionPanacheRepository divisionPanacheRepository;
    private ParkingLotPanacheRepository parkingLotPanacheRepository;
    private ParkingLotService parkingLotService;
//    private ParkingLot parkingLot;
//    private ParkingLotDto parkingLotDto;
//    private Division division;
//    private CreateParkingLotDto createParkingLotDto;

    @BeforeEach
    void setUp() {
        divisionPanacheRepository = Mockito.mock(DivisionPanacheRepository.class);
        divisionService = new DivisionService(divisionPanacheRepository);
        parkingLotPanacheRepository = Mockito.mock(ParkingLotPanacheRepository.class);
        parkingLotService = new ParkingLotService(parkingLotPanacheRepository, divisionService);

//        createParkingLotDto = new CreateParkingLotDto("parking lot a", Category.UNDERGROUND_BUILDING, 500, new CreateContactPersonDto("023456622", "0469456878", "mmm@mmm.mmm", new Address("Contact test street", "66", 1180, "Uccle")), new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);
//
//        division = new Division(1, "division a", "original name", "director name", null);
//
//        parkingLot = new ParkingLot(1, "parking lot a", Category.UNDERGROUND_BUILDING, 500, new ContactPerson("023456589", "0465789312", "lll@mmm.ddd", new Address("contact street test", "65", 1180, "Uccle")), division, new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);
    }

    @Test
    void createParkingLot_givenCreateParkingLotDtoAndDivisionId_returnsParkingLotEntity() {
        String divisionId = "1";
        Mockito.when(divisionPanacheRepository.findDivisionById(any(Long.class))).thenReturn(Optional.of(DIVISION));
        Mockito.when(parkingLotPanacheRepository.createParkingLot(any(ParkingLot.class))).thenReturn(PARKING_LOT);
        ParkingLot result = parkingLotService.createParkingLot(CREATE_PARKING_LOT_DTO, divisionId);
        assertEquals(result, PARKING_LOT);
    }

    @Test
    void createParkingLot_givenNullDivisionId_throwsIllegalArgumentExceptionWithRightMessage() {
        testCreateParkingLotDtoWithInvalidEmail(CREATE_PARKING_LOT_DTO, null, "division id must not be null");
    }

    @Test
    void createParkingLot_givenAlreadyExistingParkingLotName_throwsIllegalArgumentExceptionWithRightMessage() {
        String divisionId = "1";
        Mockito.when(parkingLotPanacheRepository.getParkingLotByName(any(String.class))).thenReturn(PARKING_LOT);
        Mockito.when(divisionPanacheRepository.findDivisionById(any(Long.class))).thenReturn(Optional.of(DIVISION));

        testCreateParkingLotDtoWithInvalidEmail(CREATE_PARKING_LOT_DTO, divisionId, "The name of the parking lot already exists. Choose another name.");
    }

    @Test
    void createParkingLot_givenNullContactPerson_throwsIllegalArgumentExceptionWithRightMessage() {
        CreateParkingLotDto createParkingLotDtoWithNullContactPerson = new CreateParkingLotDto("parking lot b", Category.UNDERGROUND_BUILDING, 500, null, new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);

        String divisionId = "1";
        Mockito.when(divisionPanacheRepository.findDivisionById(any(Long.class))).thenReturn(Optional.of(DIVISION));

        testCreateParkingLotDtoWithInvalidEmail(createParkingLotDtoWithNullContactPerson, divisionId, "Contact person is required");
    }

    @Test
    void createParkingLot_givenNullOrEmptyContactEmail_throwsIllegalArgumentExceptionWithRightMessage() {
        String emptyEmail = "";

        CreateParkingLotDto createParkingLotDtoWithNullEmail = new CreateParkingLotDto("parking lot a", Category.UNDERGROUND_BUILDING, 500, new CreateContactPersonDto("023456622", "0469456878", null, new Address("Contact test street", "66", 1180, "Uccle")), new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);

        CreateParkingLotDto createParkingLotDtoWithEmptyEmail = new CreateParkingLotDto("parking lot a", Category.UNDERGROUND_BUILDING, 500, new CreateContactPersonDto("023456622", "0469456878", emptyEmail, new Address("Contact test street", "66", 1180, "Uccle")), new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);

        String divisionId = "1";
        Mockito.when(divisionPanacheRepository.findDivisionById(any(Long.class))).thenReturn(Optional.of(DIVISION));

        IllegalArgumentException thrownNull = Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLotService.createParkingLot(createParkingLotDtoWithNullEmail, divisionId));

        IllegalArgumentException thrownEmpty = Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLotService.createParkingLot(createParkingLotDtoWithEmptyEmail, divisionId));

        Assertions.assertEquals("Contact person's email is required", thrownNull.getMessage());
        Assertions.assertEquals(thrownEmpty.getMessage(), thrownNull.getMessage());
    }

    @Test
    void createParkingLot_givenNullOrEmptyPhoneNumberAndMobileNumber_throwsIllegalArgumentExceptionWithRightMessage() {

        CreateParkingLotDto createParkingLotDtoWithNullPhoneAndMobileNumbers = new CreateParkingLotDto("parking lot a", Category.UNDERGROUND_BUILDING, 500, new CreateContactPersonDto(null, null, "test@test.test", new Address("Contact test street", "66", 1180, "Uccle")), new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);

        CreateParkingLotDto createParkingLotDtoWithEmptyPhoneAndMobileNumbers = new CreateParkingLotDto("parking lot a", Category.UNDERGROUND_BUILDING, 500, new CreateContactPersonDto("", "", "test@test.test", new Address("Contact test street", "66", 1180, "Uccle")), new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);

        String divisionId = "1";
        Mockito.when(divisionPanacheRepository.findDivisionById(any(Long.class))).thenReturn(Optional.of(DIVISION));

        IllegalArgumentException thrownNull = Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLotService.createParkingLot(createParkingLotDtoWithNullPhoneAndMobileNumbers, divisionId));

        IllegalArgumentException thrownEmpty = Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLotService.createParkingLot(createParkingLotDtoWithEmptyPhoneAndMobileNumbers, divisionId));

        Assertions.assertEquals("At least one phone number is required", thrownNull.getMessage());
        Assertions.assertEquals(thrownEmpty.getMessage(), thrownNull.getMessage());
    }

    @Test
    void createParkingLot_givenNullOrEmptyPhoneNumber_returnsParkingLotEntity() {

        CreateParkingLotDto createParkingLotDtoWithNullPhone = new CreateParkingLotDto("parking lot a", Category.UNDERGROUND_BUILDING, 500, new CreateContactPersonDto(null, "0465897452", "test@test.test", new Address("Contact test street", "66", 1180, "Uccle")), new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);

        CreateParkingLotDto createParkingLotDtoWithEmptyPhone = new CreateParkingLotDto("parking lot a", Category.UNDERGROUND_BUILDING, 500, new CreateContactPersonDto("054645252", "0465897452", "test@test.test", new Address("Contact test street", "66", 1180, "Uccle")), new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);

        ParkingLot parkingLotNullPhone = new ParkingLot(1, "parking lot a", Category.UNDERGROUND_BUILDING, 500, new ContactPerson(null, "0465789312", "lll@mmm.ddd", new Address("contact street test", "65", 1180, "Uccle")), DIVISION, new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);

        ParkingLot parkingLotEmptyPhone = new ParkingLot(1, "parking lot a", Category.UNDERGROUND_BUILDING, 500, new ContactPerson("", "0465789312", "lll@mmm.ddd", new Address("contact street test", "65", 1180, "Uccle")), DIVISION, new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);

        String divisionId = "1";
        Mockito.when(divisionPanacheRepository.findDivisionById(any(Long.class))).thenReturn(Optional.of(DIVISION));

        Mockito.when(parkingLotPanacheRepository.createParkingLot(any(ParkingLot.class))).thenReturn(parkingLotNullPhone);
        ParkingLot resultWithNullPhone = parkingLotService.createParkingLot(createParkingLotDtoWithNullPhone, divisionId);
        assertEquals(resultWithNullPhone, parkingLotNullPhone);

        Mockito.when(parkingLotPanacheRepository.createParkingLot(any(ParkingLot.class))).thenReturn(parkingLotEmptyPhone);
        ParkingLot resultWithEmptyPhone = parkingLotService.createParkingLot(createParkingLotDtoWithEmptyPhone, divisionId);
        assertEquals(resultWithEmptyPhone, parkingLotEmptyPhone);
    }

    @Test
    void createParkingLot_givenInvalidEmail_returnsIllegalArgumentExceptionWithRightMessage() {
        String invalidEmailLocalSpecialCharacter = "test$@gmail.com";
        String invalidEmailDotStartLocal = ".test@gmail.com";
        String invalidEmailDotEndLocal = "test.@gmail.com";
        String invalidEmailDotEndDomain = "test@gmail.com.";
        String invalidEmailConsecutiveDotsDomain = "test@gmail..com";
        String invalidEmailConsecutiveDotsLocal = "te..st@gmail.com";
        String invalidEmailMoreThan64Char = "testtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt@gmail..com";
        String invalidEmailHyphenDomainStart = "test@-gmail.com";
        String invalidEmailHyphenDomainEnd = "test@gmail.com-";
//        String validEmailNumericValueLocal = "test9@gmail.com";
//        String validEmailUppercaseLocal = "tesT@gmail.com";
//        String validEmailUnderscoreLocal = "test_@gmail.com";
//        String validEmailHyphenLocal = "test-@gmail.com";
//        String validEmailDotLocal = "tes.t@gmail.com";
//        String validEmailNumericDomain = "test@gma9il.com";
//        String validEmailUppercaseDomain = "test@gmAil.com";

        CreateParkingLotDto createParkingLotDtoWithInvalidEmailLocalSpecialCharacter = createCreateParkingLotDtoWithInvalidEmail(invalidEmailLocalSpecialCharacter);
        CreateParkingLotDto createParkingLotDtoWithInvalidEmailDotStartLocal = createCreateParkingLotDtoWithInvalidEmail(invalidEmailDotStartLocal);
        CreateParkingLotDto createParkingLotDtoWithInvalidEmailDotEndLocal = createCreateParkingLotDtoWithInvalidEmail(invalidEmailDotEndLocal);
        CreateParkingLotDto createParkingLotDtoWithInvalidEmailDotEndDomain = createCreateParkingLotDtoWithInvalidEmail(invalidEmailDotEndDomain);
        CreateParkingLotDto createParkingLotDtoWithInvalidEmailConsecutiveDotsDomain = createCreateParkingLotDtoWithInvalidEmail(invalidEmailConsecutiveDotsDomain);
        CreateParkingLotDto createParkingLotDtoWithInvalidEmailConsecutiveDotsLocal = createCreateParkingLotDtoWithInvalidEmail(invalidEmailConsecutiveDotsLocal);
        CreateParkingLotDto createParkingLotDtoWithInvalidEmailMoreThan64Char = createCreateParkingLotDtoWithInvalidEmail(invalidEmailMoreThan64Char);
        CreateParkingLotDto createParkingLotDtoWithInvalidEmailHyphenDomainStart = createCreateParkingLotDtoWithInvalidEmail(invalidEmailHyphenDomainStart);
        CreateParkingLotDto createParkingLotDtoWithInvalidEmailHyphenDomainEnd = createCreateParkingLotDtoWithInvalidEmail(invalidEmailHyphenDomainEnd);


        String divisionId = "1";
        Mockito.when(divisionPanacheRepository.findDivisionById(any(Long.class))).thenReturn(Optional.of(DIVISION));

        testCreateParkingLotDtoWithInvalidEmail(createParkingLotDtoWithInvalidEmailDotStartLocal, divisionId, getExpectedMessage(createParkingLotDtoWithInvalidEmailDotStartLocal));
        testCreateParkingLotDtoWithInvalidEmail(createParkingLotDtoWithInvalidEmailDotEndLocal, divisionId, getExpectedMessage(createParkingLotDtoWithInvalidEmailDotEndLocal));
        testCreateParkingLotDtoWithInvalidEmail(createParkingLotDtoWithInvalidEmailDotEndDomain, divisionId, getExpectedMessage(createParkingLotDtoWithInvalidEmailDotEndDomain));
        testCreateParkingLotDtoWithInvalidEmail(createParkingLotDtoWithInvalidEmailConsecutiveDotsDomain, divisionId, getExpectedMessage(createParkingLotDtoWithInvalidEmailConsecutiveDotsDomain));
        testCreateParkingLotDtoWithInvalidEmail(createParkingLotDtoWithInvalidEmailConsecutiveDotsLocal, divisionId, getExpectedMessage(createParkingLotDtoWithInvalidEmailConsecutiveDotsLocal));
        testCreateParkingLotDtoWithInvalidEmail(createParkingLotDtoWithInvalidEmailMoreThan64Char, divisionId, getExpectedMessage(createParkingLotDtoWithInvalidEmailMoreThan64Char));
        testCreateParkingLotDtoWithInvalidEmail(createParkingLotDtoWithInvalidEmailHyphenDomainStart, divisionId, getExpectedMessage(createParkingLotDtoWithInvalidEmailHyphenDomainStart));
        testCreateParkingLotDtoWithInvalidEmail(createParkingLotDtoWithInvalidEmailHyphenDomainEnd, divisionId, getExpectedMessage(createParkingLotDtoWithInvalidEmailHyphenDomainEnd));
    }

    private void testCreateParkingLotDtoWithInvalidEmail(CreateParkingLotDto createParkingLotDtoWithInvalidEmail, String divisionId, String expectedMessage) {
        IllegalArgumentException thrownInvalidEmail = Assertions.assertThrows(IllegalArgumentException.class, () -> parkingLotService.createParkingLot(createParkingLotDtoWithInvalidEmail, divisionId));
        Assertions.assertEquals(expectedMessage, thrownInvalidEmail.getMessage());
    }

    private static String getExpectedMessage(CreateParkingLotDto createParkingLotDtoWithInvalidEmail) {
        return "Contact person's email \"" + createParkingLotDtoWithInvalidEmail.createContactPersonDto().email() + "\" is not valid. Follow those rules : \n" + """
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
                Hyphen “-” aren’t allowed at the start and end of the domain part.\n
                Dot “.” aren’t allowed at the end of the domain part.\n
                No consecutive dots.\n        
                """;
    }

    private static CreateParkingLotDto createCreateParkingLotDtoWithInvalidEmail(String invalidEmail) {
        return new CreateParkingLotDto("parking lot a", Category.UNDERGROUND_BUILDING, 500,
                new CreateContactPersonDto("023156452", "0465897452", invalidEmail,
                        new Address("Contact test street", "66", 1180, "Uccle")),
                new Address("Parking lot test street", "77", 1000, "Brussels"),
                2, 500);
    }


}