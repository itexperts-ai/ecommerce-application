package UserAuthentication.Service;

import UserAuthentication.DTO.UserLogin;
import UserAuthentication.DTO.UserRegister;
import UserAuthentication.Entity.UserEntity;


import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity registerUser(UserRegister request);
    Optional<UserEntity> login(UserLogin login);
    Optional<UserEntity> getuser(Long id);
    List<UserEntity> getalluser();
    UserEntity updateuser(Long id,UserEntity user);

}
