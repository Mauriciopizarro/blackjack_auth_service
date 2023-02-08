package com.devdream.blackjackaccountservice.infrastructure.controllers;

import com.devdream.blackjackaccountservice.application.implementations.UserAccountRepo;
import com.devdream.blackjackaccountservice.domain.models.Admin;
import com.devdream.blackjackaccountservice.domain.models.CreateGameResponseModel;
import com.devdream.blackjackaccountservice.domain.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/management/game")
public class CreateGameController {


    @Autowired
    private  RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private final UserAccountRepo userAccountRepo;

    public User getUserDetails(String email){
        return this.userAccountRepo.getByEmail(email);
    }

    @PostMapping("/create")
    public Object createGame() throws IOException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate = restTemplateBuilder.build();
            String userEmail = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();
            String url = "http://gateway:8080/api/v1/management/game/create";
            User user = getUserDetails(userEmail);
            log.error(user.getName());
            Admin admin = new Admin(user.getName(), user.getId());
            return ResponseEntity.ok(restTemplate.postForEntity(url, admin, CreateGameResponseModel.class).getBody());
        }
        catch (HttpClientErrorException ex) {
            throw new ResponseStatusException(ex.getStatusCode(), ex.getMessage(), ex);
        }
    }
}
