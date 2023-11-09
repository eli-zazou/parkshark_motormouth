package be.motormouth.security;

import be.motormouth.exceptions.UnauthorizatedException;
import be.motormouth.exceptions.WrongPasswordException;
import be.motormouth.security.users.User;
import be.motormouth.security.users.UserRepository;
import be.motormouth.security.users.UserRole;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class SecurityServiceIT {
    //
    private User admin;
    private DecodedCredentials decodedCredentials;
    @Inject
    SecurityService securityService;
    @Inject
    UserRepository userRepository;
    @BeforeEach
    void setUp() {
        decodedCredentials = new DecodedCredentials("admin", "1234");
        admin = new User(1,"admin","03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", UserRole.MANAGER,null);
    }
    @Test
    void validateAuthorization() {
        User authorised = securityService.validateAuthorization("Basic YWRtaW46MTIzNA==", Feature.VIEW_ALL_MEMBERS);
        Assertions.assertEquals(admin.getUserId(), authorised.getUserId());
    }
    @Test
    void validateAuthorizationWithWrongPassword() {
        RuntimeException thrown = Assertions.assertThrows(WrongPasswordException.class
                , () -> securityService.validateAuthorization("Basic YWRtaW46MTIzNDU=", Feature.VIEW_ALL_MEMBERS));
    }
    @Test
    void validateAuthorizationWithNoAccess() {
        RuntimeException thrown = Assertions.assertThrows(UnauthorizatedException.class
                , () -> securityService.validateAuthorization("Basic YWRtaW46MTIzNA==", Feature.ALLOCATE_PARKING_SPOT));
    }
}
