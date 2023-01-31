package com.devdream.blackjackaccountservice.domain.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@ToString
@Document(value = "User")
public class User {

    @Id
    public String id;

    @NonNull
    public String name;

    @NonNull
    public String password;

    @NonNull
    @Indexed(unique=true)
    public String email;

}
