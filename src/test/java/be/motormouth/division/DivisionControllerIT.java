package be.motormouth.division;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class DivisionControllerIT {
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
}
