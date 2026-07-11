import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserService.class);

    public void register(String username) {
        logger.info("Registering user: {}", username);

        // business logic

        logger.info("User registered successfully");
    }
}
