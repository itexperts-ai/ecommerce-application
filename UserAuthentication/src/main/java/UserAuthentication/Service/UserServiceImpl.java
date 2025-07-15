package UserAuthentication.Service;

import UserAuthentication.DTO.UserLogin;
import UserAuthentication.DTO.UserRegister;
import UserAuthentication.Entity.UserEntity;
import UserAuthentication.Helper.UserHelper;
import UserAuthentication.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelper userHelper;

    @Transactional
    public UserEntity registerUser(UserRegister request) {
        logger.info("Starting registration for user: ");

        UserEntity user = userHelper.registerUser(request);
        UserEntity savedUser = userRepository.save(user);

        logger.info("User registered successfully with email: ");
        return savedUser;
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> login(UserLogin req) {
        logger.info("Login request for email: {}",req.getEmail());

        Optional<UserEntity> user = Optional.of(userHelper.userLogin(req));
        logger.info("Login successful for user: ");

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getuser(Long id) {
        logger.info("Fetching user by ID: ");

        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            logger.info("User found: ");
        } else {
            logger.warn("User not found for ID: ");
        }

        return user;
    }

    public List<UserEntity> getalluser() {
        logger.info("Fetching all users from the database");
        return userRepository.findAll();
    }

    public UserEntity updateuser(Long id, UserEntity userEntity) {
        logger.info("Updating user with ID: {}", id);

        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", id);
                    return new RuntimeException("User not found");
                });

        existingUser.setPhone(userEntity.getPhone());
        existingUser.setAddress(userEntity.getAddress());

        UserEntity updatedUser = userRepository.save(existingUser);
        logger.info("User updated successfully with ID: {}", updatedUser.getId());

        return updatedUser;
    }

    public void deactivateUser(String username) {
        logger.info("Deactivating user: {}", username);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("User not found with username: {}", username);
                    return new RuntimeException("User not found");
                });

        user.setActive(false);
        userRepository.save(user);

        logger.info("User {} deactivated successfully", username);
    }

    public void activateUser(String username) {
        logger.info("Activating user: {}", username);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("User not found with username: {}", username);
                    return new RuntimeException("User not found");
                });

        user.setActive(true);
        userRepository.save(user);

        logger.info("User {} activated successfully", username);
    }

    public List<UserEntity> filterUsersByName(String character) {
        logger.info("Filtering users by starting character: {}", character);

        List<UserEntity> users = userRepository.findByUsernameStartingWith(character.trim().toLowerCase());
        logger.info("Found {} users starting with: {}", users.size(), character);

        return users;
    }

    public Page<UserEntity> findUsersByPage(int offset, int pageSize) {
        logger.info("Fetching users with pagination - offset: {}, pageSize: {}", offset, pageSize);

        Page<UserEntity> userPage = userRepository.findAll(PageRequest.of(offset, pageSize));
        logger.info("Page contains {} users", userPage.getNumberOfElements());

        return userPage;
    }
}
