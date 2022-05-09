package com.denisov.anything.security.secretencryption;

import com.denisov.anything.authservice.user.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//TODO ?SecretEncryptionFactory?
public interface SecretEncryption {
    public String encrypt(String password);

    public boolean check(String secret, UserEntity user);
}
