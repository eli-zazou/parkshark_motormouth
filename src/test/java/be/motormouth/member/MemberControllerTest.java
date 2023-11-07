package be.motormouth.member;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured.*;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Tag("Integration")
@QuarkusTestResource(H2DatabaseTestResource.class)
class MemberControllerTest {

    @Test
    void getAllMembers(){

    }

}