package com.devdream.blackjackaccountservice.application.services;

import com.devdream.blackjackaccountservice.application.implementations.UserAccountRepo;
import com.devdream.blackjackaccountservice.application.services.exceptions.EmailUsedException;
import com.devdream.blackjackaccountservice.domain.models.User;
import com.devdream.blackjackaccountservice.infrastructure.security.SecurityPass;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

    @Autowired
    private final UserAccountRepo userAccountRepo;
    @Autowired
    private final SecurityPass securityPass;

    public void signUp(User user) throws EmailUsedException {

        Long count = userAccountRepo.countFetchedDocumentsForEmail(user.getEmail());
        if (count > 0){
            throw new EmailUsedException();
        }
        String hashPassword = securityPass.encodePlainPassword(user.getPassword());
        user.setPassword(hashPassword);
        userAccountRepo.saveUser(user);
    }
}
