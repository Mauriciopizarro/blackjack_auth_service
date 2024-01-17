package com.devdream.blackjackaccountservice.infrastructure.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/management/game")
public class CroupierPlayController {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @PostMapping("/croupier_play/{gameId}")
    public Object croupierPlay(@PathVariable String gameId) throws IOException {
        try {
            RestTemplate restTemplate = restTemplateBuilder.build();
            SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();

            String url = "http://blackjack-app-java:8080/api/v1/game/croupier_play/" + gameId;
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url, null, Object.class);
            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (HttpClientErrorException ex) {
            throw new ResponseStatusException(ex.getStatusCode(), ex.getMessage(), ex);
        }
    }
}
