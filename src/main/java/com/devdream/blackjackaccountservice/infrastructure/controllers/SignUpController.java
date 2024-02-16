package com.devdream.blackjackaccountservice.infrastructure.controllers;

import com.devdream.blackjackaccountservice.application.services.SignUpService;
import com.devdream.blackjackaccountservice.application.services.exceptions.EmailUsedException;
import com.devdream.blackjackaccountservice.domain.models.User;
import com.devdream.blackjackaccountservice.infrastructure.publisher.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private final Publisher publisher = new Publisher();

    @PostMapping("/register")
    public Map<String, String> signUp(@RequestBody User user) throws EmailUsedException {
        try {
            Map<String, String> response = new HashMap<>();
            signUpService.signUp(user);
            response.put("message", "User " + user.getName() + " register successfully");
            this.publisher.send(user.email);
            return response;
        }
        catch (EmailUsedException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email in use");
        }
    }
}