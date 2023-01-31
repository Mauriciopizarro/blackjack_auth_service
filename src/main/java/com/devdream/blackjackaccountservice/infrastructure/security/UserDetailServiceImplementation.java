package com.devdream.blackjackaccountservice.infrastructure.security;

import com.devdream.blackjackaccountservice.application.implementations.UserAccountRepo;
import com.devdream.blackjackaccountservice.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImplementation implements UserDetailsService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userAccountRepo.getByEmail(email);
        return new UserDetailsImplementation(user);
    }
}
