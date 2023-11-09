package be.motormouth.security;

import be.motormouth.exceptions.UnauthorizatedException;
import be.motormouth.exceptions.UnknownDivisionException;
import be.motormouth.exceptions.WrongPasswordException;
import be.motormouth.security.users.User;
import be.motormouth.security.users.UserRepository;
import be.motormouth.security.users.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SecurityServiceTest {
    private SecurityService securityService;
    private UserRepository userRepository;
    //
    private User admin;
    private DecodedCredentials decodedCredentials;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        securityService = new SecurityService(userRepository);
        decodedCredentials = new DecodedCredentials("admin", "1234");
        admin = new User(1,"admin","03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", UserRole.MANAGER,null);
    }

    @Test
    void validateAuthorization() {
        Mockito
                .when(userRepository.checkUserId("admin"))
                .thenReturn(Optional.of(admin));
        User authorised = securityService.validateAuthorization("Basic YWRtaW46MTIzNA==", Feature.VIEW_ALL_MEMBERS);
        Assertions.assertEquals(admin, authorised);
    }
    @Test
    void validateAuthorizationWithWrongPassword() {
        Mockito
                .when(userRepository.checkUserId("admin"))
                .thenReturn(Optional.of(admin));
        RuntimeException thrown = Assertions.assertThrows(WrongPasswordException.class
                , () -> securityService.validateAuthorization("Basic YWRtaW46MTIzNDU=", Feature.VIEW_ALL_MEMBERS));
    }
    @Test
    void validateAuthorizationWithNoAccess() {
        Mockito
                .when(userRepository.checkUserId("admin"))
                .thenReturn(Optional.of(admin));
        RuntimeException thrown = Assertions.assertThrows(UnauthorizatedException.class
                , () -> securityService.validateAuthorization("Basic YWRtaW46MTIzNA==", Feature.ALLOCATE_PARKING_SPOT));
    }
}