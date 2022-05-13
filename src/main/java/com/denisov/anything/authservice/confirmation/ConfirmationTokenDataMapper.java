package com.denisov.anything.authservice.confirmation;

import com.denisov.anything.authservice.DataMapperInterface;
import com.denisov.anything.authservice.user.ConfirmationTokenEntity;

public class ConfirmationTokenDataMapper implements DataMapperInterface<ConfirmationTokenEntity, ConfirmationToken>{
    @Override
    public ConfirmationTokenEntity domainToEntity(ConfirmationToken confirmationToken){
        if(confirmationToken == null){
            return null;
        }

        ConfirmationTokenEntity entity = new ConfirmationTokenEntity();
        entity.setToken(confirmationToken.getToken());
        entity.setCreationTime(confirmationToken.getCreationTime());
        entity.setConfirmationTime(confirmationToken.getConfirmationTime());
        entity.setExpirationTime(confirmationToken.getExpirationTime());

        return entity;
    }

    @Override
    public ConfirmationToken entityToDomain(ConfirmationTokenEntity confirmationTokenEntity) {
        if(confirmationTokenEntity == null){
            return null;
        }

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setId(confirmationTokenEntity.getId());
        confirmationToken.setToken(confirmationTokenEntity.getToken());
        confirmationToken.setCreationTime(confirmationTokenEntity.getCreationTime());
        confirmationToken.setConfirmationTime(confirmationTokenEntity.getConfirmationTime());
        confirmationToken.setExpirationTime(confirmationTokenEntity.getExpirationTime());

        return confirmationToken;
    }
}
