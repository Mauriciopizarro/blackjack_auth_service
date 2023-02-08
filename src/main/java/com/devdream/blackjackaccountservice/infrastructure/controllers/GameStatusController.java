package com.devdream.blackjackaccountservice.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/game")
public class GameStatusController {

    private final RestTemplate restTemplate;

    public GameStatusController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/status/{gameId}")
    public Object getStatus(@PathVariable String gameId) throws IOException, HttpClientErrorException {
        try {
            String url = ("http://gateway:8080/api/v1/game/status/" + gameId);
            ObjectMapper mapper = new ObjectMapper();
            ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
            String body = response.getBody();
            return mapper.readTree(body);
        }
        catch (HttpClientErrorException ex) {
            throw new ResponseStatusException(ex.getStatusCode(), ex.getMessage(), ex);
        }
    }
}
