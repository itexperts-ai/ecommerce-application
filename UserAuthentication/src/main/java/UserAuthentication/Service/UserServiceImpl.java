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


    public List<UserEntity> getalluser(){
        return userRepository.findAll();
    }

    public UserEntity updateuser(Long id, UserEntity userEntity) {
            UserEntity existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            existingUser.setPhone(userEntity.getPhone());
            existingUser.setAddress(userEntity.getAddress());

            return userRepository.save(existingUser);
        }

    public void deactivateUser(String username) {
        UserEntity user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        userRepository.save(user);
    }

    public void activateUser(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(true);
        userRepository.save(user);

    }

    public List<UserEntity> filterUsersByName(String character) {
       // String prefix = character.trim().substring(0,1);
        return userRepository.findByUsernameStartingWith(character.trim().toLowerCase());
    }

    public Page<UserEntity>  findUsersByPage(int offset, int pageSize) {
       Page<UserEntity> userEntities = userRepository.findAll(PageRequest.of(offset,pageSize));
       return userEntities;
    }
}
