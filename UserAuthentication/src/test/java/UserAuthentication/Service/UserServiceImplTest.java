package UserAuthentication.Service;

import UserAuthentication.DTO.UserLogin;
import UserAuthentication.DTO.UserRegister;
import UserAuthentication.Entity.UserEntity;
import UserAuthentication.Helper.UserHelper;
import UserAuthentication.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserHelper userHelper;

    @InjectMocks
    private UserServiceImpl userService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_success() {
        UserRegister request = new UserRegister();
        request.setName("anoop");
        request.setEmail("anoop@gmail.com");
        request.setPassword("Password@123");

        UserEntity user = new UserEntity();
        user.setUsername("anoop");
        user.setEmail("anoop@gmail.com");
        user.setPassword("Password@123");

        when(userHelper.registerUser(request)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        UserEntity result = userService.registerUser(request);

        assertEquals("anoop", result.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testLogin_success() {
        UserLogin login = new UserLogin();
        login.setEmail("anoop@gmail.com");
        login.setPassword("Password@123");

        UserEntity user = new UserEntity();
        user.setEmail("anoop@gmail.com");
        user.setPassword("Password@123");

        when(userHelper.userLogin(login)).thenReturn(user);

        Optional<UserEntity> result = userService.login(login);

        assertTrue(result.isPresent());
        assertEquals("anoop@gmail.com", result.get().getEmail());
    }

    @Test
    void testGetUser_success() {
        Long userId = 1L;
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setUsername("anoop");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<UserEntity> result = userService.getuser(userId);

        assertTrue(result.isPresent());
        assertEquals("anoop", result.get().getUsername());
    }

    @Test
    void testGetUser_notFound() {
        Long userId = 2L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<UserEntity> result = userService.getuser(userId);

        assertFalse(result.isPresent());
    }
}

