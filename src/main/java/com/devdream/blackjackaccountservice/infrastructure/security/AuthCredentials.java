package com.devdream.blackjackaccountservice.infrastructure.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}
