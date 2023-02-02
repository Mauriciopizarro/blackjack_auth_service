package com.devdream.blackjackaccountservice.application.implementations;

import com.devdream.blackjackaccountservice.domain.models.User;
import com.devdream.blackjackaccountservice.infrastructure.repositories.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAccountRepo {

    @Autowired
    private final UserAccountRepository userAccountRepository;

    public void saveUser(User user){
        userAccountRepository.save(user);
    }

    public User getById(String id){
        return userAccountRepository.findById(id).get();
    }

    public void updateUser(User user){
        saveUser(user);
    }

    public User getByEmail(String email){
        return userAccountRepository.getUserByEmail(email);
    }

    public long countFetchedDocumentsForEmail(String email){
        return userAccountRepository.countFetchedDocumentsForEmail(email);
    }

    public List<User> findAll(){
        return userAccountRepository.findAll();
    }

}
