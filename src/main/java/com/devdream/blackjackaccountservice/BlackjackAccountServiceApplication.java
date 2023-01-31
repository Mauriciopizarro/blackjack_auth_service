package com.devdream.blackjackaccountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// (exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class BlackjackAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackjackAccountServiceApplication.class, args);
	}

}
