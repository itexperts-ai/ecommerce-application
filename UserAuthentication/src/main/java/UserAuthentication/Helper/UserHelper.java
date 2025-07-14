package UserAuthentication.Helper;

import UserAuthentication.DTO.UserRegister;
import UserAuthentication.DTO.UserLogin;
import UserAuthentication.Entity.UserEntity;
import UserAuthentication.Exception.ResourceNotFoundException;
import UserAuthentication.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserHelper {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserHelper.class);


    public UserEntity registerUser(UserRegister request) {
        logger.info("Registering user with name: {}", request.getName());

        if (request.getName() == null || request.getName().trim().isEmpty()) {
            logger.warn("Username is null or empty");
            throw new ResourceNotFoundException("Username cannot be null or empty.");
        }

        if (request.getName().trim().length() < 3) {
            logger.warn("Username is too small: {}", request.getName());
            throw new ResourceNotFoundException("Username must be at least 3 characters");
        }

        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            logger.warn("Email is null or empty: {}",request.getEmail());
            throw new ResourceNotFoundException("Email cannot be empty.");
        }
        if (!isValidEmail(request.getEmail().trim())) {
            logger.warn("Invalid email format: {}", request.getEmail());
            throw new ResourceNotFoundException("Invalid email format. Example: anoop123@gmail.com");
        }

        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            logger.warn("password null or empty: {}", request.getPassword());
            throw new ResourceNotFoundException("Password cannot be empty.");
        }
        if (request.getPassword().trim().length() < 8) {
            logger.warn("Password must be at least 8 characters: {}", request.getPassword());
            throw new ResourceNotFoundException("Password must be at least 8 characters");
        }
        if (!isStrongPassword(request.getPassword().trim())) {
            logger.warn("Password not recommended");
            throw new ResourceNotFoundException("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }

        if (userRepository.existsByUsername(request.getName())) {
            logger.warn("Username already exists: {}", request.getName());
            throw new ResourceNotFoundException("Username already exists. Try again...");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            logger.warn("Email already registered: {}", request.getEmail());
            throw new ResourceNotFoundException("Email already registered.");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setActive(request.isActive());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        logger.info("User data validated, ready for saving.");
        return user;
    }

    public UserEntity userLogin(UserLogin login) {
        logger.info("Attempting login for email: {}", login.getEmail());

        UserEntity user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> {
                    logger.error("Login failed. Email not registered: {}", login.getEmail());
                    return new ResourceNotFoundException("User not registered");
                });

        if (!user.getPassword().equals(login.getPassword())) {
            logger.error("Invalid password for email: {}", login.getEmail());
            throw new ResourceNotFoundException("Invalid password.");
        }

        logger.info("Login successful for user: {}", user.getUsername());
        return user;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private boolean isStrongPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
    }

    public UserEntity updateUser(Long id, UserEntity userEntity)
    {
        UserEntity userOptional = (userRepository.findById(id).get());
         userOptional.setPhone(userEntity.getPhone());
         userOptional.setAddress(userEntity.getAddress());
       return userOptional;
    }
}
