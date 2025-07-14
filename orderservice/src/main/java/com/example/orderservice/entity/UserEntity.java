package com.example.orderservice.entity;


import lombok.Data;

@Data
public class UserEntity {

    public Long id;
    public String username;
    public String email;
    public String password;
}
