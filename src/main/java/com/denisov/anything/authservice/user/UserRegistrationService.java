package com.denisov.anything.authservice.user;

import com.denisov.anything.authservice.confirmation.ConfirmationTokenDataMapper;
import com.denisov.anything.authservice.confirmation.ConfirmationTokenEntity;
import com.denisov.anything.authservice.confirmation.TokenRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.denisov.anything.security.secretencryption.BCryptSecretEncryption;
import com.denisov.anything.security.secretencryption.SecretEncryption;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserRegistrationService {
    private final UserDataMapper userDataMapper = new UserDataMapper();
    private final UserRepository userRepository;
    private final ConfirmationTokenDataMapper confirmationTokenDataMapper = new ConfirmationTokenDataMapper();
    private final TokenRepository tokenRepository;

    private SecretEncryption secretEncoder;

    public UserRegistrationService(UserRepository userRepository, TokenRepository tokenRepository){
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    //TODO: Validate data
    public String registerUser(User userToAdd){
        secretEncoder = new BCryptSecretEncryption();

        try {
            //put user into db
            UserEntity userEntity = userDataMapper.domainToEntity(userToAdd);
            userEntity.setPassword(secretEncoder.encrypt(userEntity.getPassword()));
            userRepository.save(userEntity);

            //generate and put token into db
            String token = RandomStringUtils.random(5, "abcdefghijklmnopqrstuvwxyz1234567890");
            LocalDateTime currentTime = LocalDateTime.now();
            ConfirmationTokenEntity tokenEntity = new ConfirmationTokenEntity(token, currentTime, null,
                                                                              currentTime.plusMinutes(10), userEntity);
            tokenRepository.save(tokenEntity);

        } catch(DataIntegrityViolationException sqlException){
            sqlException.printStackTrace();
            String message = sqlException.getRootCause().getMessage();
            return "error: " + message.substring(message.indexOf("(") + 1, message.indexOf(")")) + " already used";
        }

        return "successful";
    }

    public String confirmUser(String token){
        ConfirmationTokenEntity confirmationTokenEntity;
        Optional<ConfirmationTokenEntity> opt = tokenRepository.findByToken(token);

        if(opt.isEmpty()){
            return "error: token not found";
        } else {
            confirmationTokenEntity = opt.get();
        }

        LocalDateTime confirmedAt = LocalDateTime.now();

        if(confirmedAt.isAfter(confirmationTokenEntity.getExpirationTime())){
            return "error: token expired";
        }

        tokenRepository.updateConfirmedAt(confirmedAt, confirmationTokenEntity.getId());
        return "successful";
    }
}
