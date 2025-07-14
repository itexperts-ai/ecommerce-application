package com.product.service;

import com.product.entity.UserLoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserLoginRequestService {
    UserLoginRequest getByUserNameAndPassword(String email,String password);
}
