package com.devdream.blackjackaccountservice.infrastructure.controllers;
import com.devdream.blackjackaccountservice.application.implementations.UserAccountRepo;
import com.devdream.blackjackaccountservice.domain.models.CreateGameRequestData;
import com.devdream.blackjackaccountservice.domain.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
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

    Logger logger = LoggerFactory.getLogger(CreateGameController.class);

    @Autowired
    private  RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private UserAccountRepo userAccountRepo;

    public User getUserDetails(String email){
        return this.userAccountRepo.getByEmail(email);
    }

    @PostMapping("/create")
    public Object createGame() throws IOException {
        try {
            RestTemplate restTemplate = restTemplateBuilder.build();
            String userEmail = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();

            User user = getUserDetails(userEmail);
            String url = "http://blackjack-app-java:8080/api/v1/game/create";
            CreateGameRequestData requestData = new CreateGameRequestData(user.getName(), user.getId());
            HttpEntity<CreateGameRequestData> requestEntity = new HttpEntity<>(requestData);
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url, requestEntity, Object.class);
            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (HttpClientErrorException ex) {
            throw new ResponseStatusException(ex.getStatusCode(), ex.getMessage(), ex);
        }
    }
}
