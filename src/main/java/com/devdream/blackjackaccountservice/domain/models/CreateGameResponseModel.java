package com.devdream.blackjackaccountservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateGameResponseModel {

    public final String message = "Game created";
    public String id;
    public Admin admin;

}
