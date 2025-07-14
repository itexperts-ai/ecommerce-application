package com.product.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class UserLoginRequest {
    Long id;
    String username;
    String password;
    String email;
}
