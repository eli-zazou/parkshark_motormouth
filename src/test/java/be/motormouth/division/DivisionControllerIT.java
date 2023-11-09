package be.motormouth.division;

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

import static io.restassured.RestAssured.given;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.CREATED;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class DivisionControllerIT {
    @Inject
    ParkingLotPanacheRepository parkingLotPanacheRepository;

    @Test
    void viewAllDivisions(){
        given()
                .when()
                .auth()
                .preemptive()
                .basic("admin", "1234")
                .get("/divisions")
                .then()
                .statusCode(200);
    }
    @Test
    void viewDivisionsById(){
        given()
                .pathParam("id", 1)
                .when()
                .auth()
                .preemptive()
                .basic("admin", "1234")
                .get("/divisions/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    void createParkingLot(){
        CreateParkingLotDto createParkingLotXXDto = new CreateParkingLotDto("parking lot XX", Category.UNDERGROUND_BUILDING, 500, new CreateContactPersonDto("023456622", "0469456878", "mmm@mmm.mmm", new Address("Contact test street", "66", 1180, "Uccle")), new Address("Parking lot test street", "77", 1000, "Brussels"), 2, 500);

        given()
                .pathParam("id", 1)
                .auth()
                .preemptive()
                .basic("admin", "1234")
                .header("Content-type", "application/json")
                .and()
                .body(createParkingLotXXDto)
                .when()
                .post("/divisions/{id}/parkinglot")
                .then()
                .statusCode(CREATED);
    }
}
