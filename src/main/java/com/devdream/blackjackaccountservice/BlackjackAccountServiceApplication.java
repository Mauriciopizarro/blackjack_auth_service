package com.devdream.blackjackaccountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@ComponentScan(basePackages = {"com.devdream.blackjackaccountservice", "com.devdream.blackjackaccountservice.infrastructure.publisher"})
public class BlackjackAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackjackAccountServiceApplication.class, args);
	}

	@Bean
	public HealthIndicator customHealthIndicator() {
		return () -> Health.up().withDetail("message", "Service is running").build();
	}

}