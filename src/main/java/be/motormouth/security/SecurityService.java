package be.motormouth.security;

import be.motormouth.exceptions.*;
import be.motormouth.security.users.User;
import be.motormouth.security.users.UserRepository;
import com.google.common.hash.Hashing;
import jakarta.annotation.Nullable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import static java.lang.String.format;

@ApplicationScoped
@Transactional
public class SecurityService {
    private final UserRepository userRepository;
    private final Logger logger = Logger.getLogger(SecurityService.class);

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User validateAuthorization(@Nullable String authorization, Feature feature) {
        DecodedCredentials credentials = getUsernamePassword(Optional.ofNullable(authorization)
                .orElseThrow(UnauthorizatedException::new));
        User user = userRepository.checkUserId(credentials.getUsername())
                .orElseThrow(UnknownUserException::new);

        if (!user.doesPasswordMatch(credentials.getPassword())) {
            logger.infof("Password does not match for user %s", credentials.getUsername());
            throw new WrongPasswordException();
        }

        if (!user.canHaveAccessTo(feature)) {
            logger.infof(format("User %s does not have access to %s", credentials.getUsername(), feature));
            throw new UnauthorizatedException();
        }
        return user;
    }

    public DecodedCredentials getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":")).toLowerCase();
        String password = Hashing.sha256().hashString(decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1), StandardCharsets.UTF_8).toString();
        return new DecodedCredentials(username, password);
    }
}
