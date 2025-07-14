package com.example.orderservice.impl;

import com.example.orderservice.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Service
public class RestTemplateProvider {

    @Autowired
    private RestTemplate restTemplate;

    public Optional<UserEntity> getUserData(Long id){
        try {
            return Optional.ofNullable(restTemplate.getForObject("http://192.168.1.9:8080/api/v1/user/" + id, UserEntity.class));
        }
        catch (RestClientException e){
            System.out.println("Error fetching data: " + e.getMessage());
            return null;
        }
        }
    }

