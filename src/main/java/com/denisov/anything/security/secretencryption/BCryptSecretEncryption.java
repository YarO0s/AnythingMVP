package com.denisov.anything.security.secretencryption;

import com.denisov.anything.authservice.user.UserEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptSecretEncryption implements SecretEncryption {

    @Override
    public String encrypt(String secret){
        String hash = BCrypt.hashpw(secret, BCrypt.gensalt());
        return hash;
    }

    @Override
    public boolean check(String secret, UserEntity user){
        return BCrypt.checkpw(secret, user.getPassword());
    }
}
