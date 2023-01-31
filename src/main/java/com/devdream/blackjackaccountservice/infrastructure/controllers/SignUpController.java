package com.devdream.blackjackaccountservice.infrastructure.controllers;

import com.devdream.blackjackaccountservice.application.services.SignUpService;
import com.devdream.blackjackaccountservice.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class SignUpController {

    @Autowired
    private final SignUpService signUpService;

    @PostMapping("/sign_up")
    public Map<String, String> signUp(@RequestBody User user){
        Map<String, String> response = new HashMap<>();
        signUpService.signUp(user);
        response.put("message", "User " + user.getName() + " register successfully");
        return response;
    }
}
