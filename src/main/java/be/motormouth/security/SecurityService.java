package be.motormouth.security;
import org.jboss.logmanager.Logger;

public class SecurityService {

//    private final Logger logger = Logger.getLogger(SecurityService.class);
//
//    private final UserRepository userRepository;
//
//    public SecurityService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public User validateAuthorization(@Nullable String authorization, Feature feature) {
//        DecodedCredentials credentials = getUsernamePassword(Optional.ofNullable(authorization)
//                .orElseThrow(UnauthorizatedException::new));
//        User user = userRepository.checkUserId(credentials.getUsername())
//                .orElseThrow(UnknownUserException::new);
//
//        if (!user.doesPasswordMatch(credentials.getPassword())) {
//            logger.errorf("Password does not match for user %s", credentials.getUsername());
//            throw new WrongPasswordException();
//        }
//
//        if (!user.canHaveAccessTo(feature)) {
//            logger.error(format("User %s does not have access to %s", credentials.getUsername(), feature));
//            throw new UnauthorizatedException();
//        }
//        return user;
//    }
//
//    public DecodedCredentials getUsernamePassword(String authorization) {
//        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
//        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":")).toLowerCase();
//        String password = Hashing.sha256().hashString(decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1), StandardCharsets.UTF_8).toString();
//        return new DecodedCredentials(username, password);
//    }
}
