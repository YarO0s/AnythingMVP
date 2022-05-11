package com.denisov.anything.authservice.user;

import com.denisov.anything.authservice.confirmation.ConfirmationTokenEntity;
import com.denisov.anything.authservice.confirmation.TokenRepository;
import com.denisov.anything.security.secretencryption.BCryptSecretEncryption;
import com.denisov.anything.security.secretencryption.SecretEncryption;
import com.denisov.anything.security.securityconfig.JWTService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JWTService jwtService = new JWTService();

    private final SecretEncryption passwordEncoder = new BCryptSecretEncryption();

    public AuthenticationService(UserRepository userRepository,
                                 TokenRepository tokenRepository){
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public String authenticate(String identifier, String secret){
        Optional<ConfirmationTokenEntity> optToken;
        UserEntity userEntity = identifyUserEntity(identifier);
        String jwt = "";

        try {
            if(userEntity == null){
                return "error: user not found";
            }

            optToken = tokenRepository.findByAppUserId(userEntity); //here

                if (optToken == null) {
                    return "error: token not found";
                }

                ConfirmationTokenEntity confirmationToken = optToken.get();

                //TODO: regenerate token and send again
                if (confirmationToken.getConfirmationTime() == null) {
                    return "error: account not confirmed";
                }

                //TODO:Email or nickname
                //TODO:All response in json
                String encryptedSecret = passwordEncoder.encrypt(secret);
                if (passwordEncoder.check(secret, userEntity)) {
                    jwt = jwtService.generateJWT(userEntity);
                }
            } catch(Exception e){
                e.printStackTrace();
                return "error: authentication service error";
            }
        return "successful: " + jwt;
    }

    private UserEntity identifyUserEntity(String identifier){
        Optional<UserEntity> optUserByName = userRepository.findByName(identifier);
        Optional<UserEntity> optUserByEmail = userRepository.findByEmail(identifier);
        UserEntity entityToReturn = null;

        if(optUserByName.isPresent()){
            entityToReturn = optUserByName.get();
        } else if(optUserByEmail.isPresent()){
            entityToReturn = optUserByEmail.get();
        }

        return entityToReturn;
    }
}
