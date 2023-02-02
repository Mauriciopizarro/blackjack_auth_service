package com.devdream.blackjackaccountservice.infrastructure.repositories;

import com.devdream.blackjackaccountservice.domain.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends MongoRepository<User, String> {

    @Query(value = "{email :?0}", count = true)
    Long countFetchedDocumentsForEmail(String email);

    @Query("{email :?0}")
    User getUserByEmail(String email);

}
