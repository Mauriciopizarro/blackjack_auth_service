package com.devdream.blackjackaccountservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateGameRequestData {

    public String name;
    public String id;
}
