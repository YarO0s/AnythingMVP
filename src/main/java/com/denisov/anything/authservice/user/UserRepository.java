package com.denisov.anything.authservice.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    public Optional<UserEntity> findByName(String name);

    public Optional<UserEntity> findByEmail(String email);
}
