package be.motormouth.security;

import be.motormouth.security.users.User;
import be.motormouth.security.users.UserRepository;
import be.motormouth.security.users.UserRole;
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
    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        securityService = new SecurityService(userRepository);
        admin = new User("admin","03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4", UserRole.MANAGER,null);
    }

//    @Test
//    void validateAuthorization() {
//        Mockito
//                .when(()-> userRepository.checkUserId())
//                .thenReturn(Optional.of(admin));
//    }
}