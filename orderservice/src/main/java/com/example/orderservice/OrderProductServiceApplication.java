package com.example.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.client.RestTemplate;

//@EnableWebSecurity
@SpringBootApplication
public class OrderProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderProductServiceApplication.class, args);
		System.out.println(" * * * * W E L C O M E - A R P I T * * * * ");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
