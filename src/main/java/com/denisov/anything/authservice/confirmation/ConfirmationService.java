package com.denisov.anything.authservice.confirmation;

import org.springframework.stereotype.Service;

@Service
public class ConfirmationService{
    private final TokenRepository tokenRepository;

    public ConfirmationService(TokenRepository tokenRepository){
        this.tokenRepository = tokenRepository;
    }

    public String confirm(ConfirmationToken token){
        return "";
    }
}
