package com.denisov.anything.authservice.confirmation;

import com.denisov.anything.authservice.user.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenRepository extends CrudRepository<ConfirmationTokenEntity, Long> {
    public Optional<ConfirmationTokenEntity> findByToken(String token);
    public Optional<ConfirmationTokenEntity> findByAppUserId(UserEntity userEntity);

    @Modifying
    @Transactional
    @Query(value = "UPDATE confirmation_token SET confirmation_time = ?1 WHERE id = ?2", nativeQuery = true)
    public int updateConfirmedAt(LocalDateTime confirmedAt, Long id);
}
