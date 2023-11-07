package be.motormouth.security.users;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import org.jboss.logging.Logger;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    private final Logger logger = Logger.getLogger(UserRepository.class);

    public User addUser(User user) {
        persist(user);
        return user;
    }

    public String validateUser(String user) {
        if (user == null || user.isEmpty()) {
            String msg = "User not filled in";
            logger.errorf(msg);
            throw new IllegalArgumentException(msg);
        }
        Optional<User> result = checkUserId(user);
        if (result.isPresent()) {
            String msg = "User " + user + " not unique";
            logger.errorf(msg);
            throw new IllegalArgumentException(msg);
        }
        return user;
    }

    public Optional<User> checkUserId(String userId) {
        Optional<User> user;
        try {
            user = Optional.ofNullable(find("userId = ?1", userId.toLowerCase()).singleResult());
        } catch (NoResultException noResultException) {
            logger.info("UserId " + userId + "not found");
            user = Optional.empty();
        }
        return user;
    }
}
