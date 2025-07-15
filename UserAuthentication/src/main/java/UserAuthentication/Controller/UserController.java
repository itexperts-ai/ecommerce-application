package UserAuthentication.Controller;

import UserAuthentication.DTO.UserLogin;
import UserAuthentication.DTO.UserRegister;
import UserAuthentication.Entity.UserEntity;
import UserAuthentication.Exception.ResourceNotFoundException;
import UserAuthentication.Repository.UserRepository;
import UserAuthentication.Service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static UserAuthentication.util.UserServiceConstent.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(USER)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(REGISTER)
    public ResponseEntity<UserEntity> register(@RequestBody UserRegister request) {
        logger.info("Registering user: {}", request.getName());
        UserEntity user = userService.registerUser(request);
        logger.info("User registered successfully: {}", user.getUsername());
        return ResponseEntity.ok(user);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<Optional<UserEntity>> login(@RequestBody UserLogin request) {
        logger.info("User login attempt for email: {}", request.getEmail());
        Optional<UserEntity> user = userService.login(request);
        logger.info("User login success: {}", user.map(UserEntity::getUsername).orElse("Unknown"));
        return ResponseEntity.ok(user);
    }

    @GetMapping(GETUSER)
    public ResponseEntity<Optional<UserEntity>> getuser(@PathVariable(value = "id") Long id) {
        logger.info("Fetching user by ID: {}", id);
        if (!userRepository.existsById(id)) {
            logger.warn("User not found for ID: {}", id);
            throw new ResourceNotFoundException("User not found");
        }
        return ResponseEntity.ok(userService.getuser(id));
    }

    @GetMapping(GETALLUSERS)
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        logger.info("Request received to fetch all users");

        List<UserEntity> users = userService.getalluser();

        if (users.isEmpty()) {
            logger.warn("No users found in database");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        logger.info("Returning {} users", users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateuser(@PathVariable Long id, @RequestBody UserEntity userEntity) {
        logger.info("Request received to update user with ID: {}", id);

        UserEntity updatedUser = userService.updateuser(id, userEntity);

        logger.info("User updated successfully with ID: {}", updatedUser.getId());
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/deactivate/{username}")
    public ResponseEntity<String> deactivateUser(@PathVariable String username) {
        logger.info("Request received to deactivate user: {}", username);

        userService.deactivateUser(username);

        logger.info("User {} deactivated successfully", username);
        return ResponseEntity.ok("User deactivated successfully");
    }

    @PostMapping("/activate/{username}")
    public ResponseEntity<String> activateUser(@PathVariable String username) {
        logger.info("Request received to activate user: {}", username);

        userService.activateUser(username);

        logger.info("User {} activated successfully", username);
        return ResponseEntity.ok("User activated successfully");
    }

    @GetMapping("/filter/{character}")
    public ResponseEntity<List<UserEntity>> filterUsersByName(@PathVariable String character) {
        logger.info("Filtering users by character: {}", character);

        List<UserEntity> users = userService.filterUsersByName(character);

        if (users.isEmpty()) {
            logger.warn("No users found starting with: {}", character);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        logger.info("Found {} users starting with: {}", users.size(), character);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/page/{offset}/{pageSize}")
    public ResponseEntity<Page<UserEntity>> getUsersByPage(@PathVariable int offset, @PathVariable int pageSize) {
        logger.info("Request received for paginated users - offset: {}, pageSize: {}", offset, pageSize);

        Page<UserEntity> users = userService.findUsersByPage(offset, pageSize);

        if (users.isEmpty()) {
            logger.warn("No users found for the given page parameters");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        logger.info("Returning page with {} users", users.getNumberOfElements());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
