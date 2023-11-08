package be.motormouth.member;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class MemberControllerTest {

    @Test
    void getAllMembers(){

        given()
                .when()
                .auth()
                .preemptive()
                .basic("admin", "1234")
                .get("/members")
                .then()
                .statusCode(200);
    }

    @Test
    void getMemberById(){
        given()
                .pathParam("id", 1)
                .when()
                .auth()
                .preemptive()
                .basic("admin", "1234")
                .get("/members/{id}")
                .then()
                .statusCode(200);
    }

}