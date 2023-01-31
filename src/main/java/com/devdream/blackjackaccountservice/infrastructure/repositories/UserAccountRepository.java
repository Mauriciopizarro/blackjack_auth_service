package com.devdream.blackjackaccountservice.infrastructure.repositories;

import com.devdream.blackjackaccountservice.domain.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends MongoRepository<User, String> {

    @Query("{email :?0}")
    Optional<User> getUserByEmail(String email);

}
