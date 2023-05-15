package com.egeekster.Instagram.service;

import com.egeekster.Instagram.model.AuthenticationToken;
import com.egeekster.Instagram.model.Users;
import com.egeekster.Instagram.repository.ITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    ITokenRepository tokenRepository;

    public void saveToken(AuthenticationToken token){
        tokenRepository.save(token);
    }

    public boolean authenticate(String email, String token) {
        if(email==null && token==null){
            return false;
        }
        AuthenticationToken authenticationToken=tokenRepository.findFirstByToken(token);

        if(authenticationToken==null){
            return false;
        }
        String messageBody=authenticationToken.getUsers().getEmail();

        return messageBody.equals(email);
    }

    public void deleteToken(String token) {
        AuthenticationToken token1 = tokenRepository.findFirstByToken(token);

        tokenRepository.deleteById(token1.getTokenId());
    }

}
