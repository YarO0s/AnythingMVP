package com.denisov.anything.authservice.user;

import com.denisov.anything.authservice.DataMapperInterface;

public class UserDataMapper implements DataMapperInterface<UserEntity, User> {

    @Override
    public UserEntity domainToEntity(User user){
        if(user == null){
            return null;
        }

        UserEntity entity = new UserEntity();
        //entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setName(user.getName());

        return entity;
    }

    @Override
    public User entityToDomain(UserEntity entity){
        if(entity == null){
            return null;
        }

        User user = new User();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setName(entity.getName());

        return user;
    }
}
