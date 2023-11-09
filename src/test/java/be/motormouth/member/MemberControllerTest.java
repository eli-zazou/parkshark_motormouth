package be.motormouth.member;

import be.motormouth.member.dto.CreateMemberDto;
import be.motormouth.member.entities.Address;
import be.motormouth.member.entities.LicensePlate;
import be.motormouth.member.entities.Member;
import be.motormouth.member.entities.MembershipLevel;
import be.motormouth.member.services.MemberService;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class MemberControllerTest {

    @Test
    void getAllMembers_withoutAuthorization(){
        given()
                .when()
                .get("/members")
                .then()
                .statusCode(401);
    }

    @Test
    void getAllMembers(){
        given()
                .when()
                .auth()
                .preemptive()
                .basic("manager", "1234")
                .get("/members")
                .then()
                .body("size()", equalTo(3))
                .body("id", hasItems(1, 2, 3))
                .body("firstName", hasItems("Zineb", "Eli", "aaa"))
                .body("address.streetNumber", hasItems("17A", "18"))
                .statusCode(200);
    }

    @Test
    void getMemberById_withoutAuthorization(){
        given()
                .pathParam("id", 1)
                .when()
                .get("/members/{id}")
                .then()
                .statusCode(401);
    }

    @Test
    void getMemberById_AsAMember(){
        given()
                .pathParam("id", 1)
                .when()
                .auth()
                .preemptive()
                .basic("member", "1234")
                .get("/members/{id}")
                .then()
                .statusCode(401);
    }

    @Test
    void getMemberById_AsManager(){
        given()
                .pathParam("id", 1)
                .auth()
                .preemptive()
                .basic("manager", "1234")
                .get("/members/{id}")
                .then()
                .body("firstName", equalTo("Zineb"))
                .body("lastName", equalTo("El tuti"))
                .body("phoneNumber", equalTo("0123456789"))
                .body("registrationDate", equalTo("2023-11-07"))
                .body("membershipLevel", equalTo("BRONZE"))
                .body("address.streetNumber", equalTo("17A"))
                .body("address.postalCode", equalTo(1150))
                .statusCode(200);
    }

    @Test
    void createMember(){
        JsonObject address = Json.createObjectBuilder()
                .add("streetName", "OUIouiLaan")
                .add("streetNumber", "10")
                .add("postLabel", "Beau Taxi")
                .add("postalCode", 10)
                .build();
        JsonObject licensePlate = Json.createObjectBuilder()
                .add("licensePlateNumber", "OUI-OUI")
                .add("issuingCountry", "NE")
                .build();
        JsonObject createMemberDto = Json.createObjectBuilder()
                .add("firstName", "oui")
                .add("lastName", "oui")
                .add("phoneNumber", "062453")
                .add("emailAddress", "taxi@gmail.com")
                .add("address", address)
                .add("licensePlate", licensePlate)
                .add("MembershipLevel", "GOLD")
                .add("username", "ouioui")
                .add("password", "1234")
                .build();
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(createMemberDto.toString())
                .when()
                .post("/members")
                .then()
                .body("id", equalTo(4))
                .body("firstName", equalTo("oui"))
                .body("registrationDate", equalTo(String.valueOf(LocalDate.now())))
                .statusCode(201);
    }
}