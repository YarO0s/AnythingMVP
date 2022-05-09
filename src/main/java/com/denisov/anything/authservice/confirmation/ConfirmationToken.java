package com.denisov.anything.authservice.confirmation;

import java.time.LocalDateTime;

public class ConfirmationToken {
    private long id;
    private String token;
    private LocalDateTime creationTime;
    private LocalDateTime confirmationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getConfirmationTime() {
        return confirmationTime;
    }

    public void setConfirmationTime(LocalDateTime confirmationTime) {
        this.confirmationTime = confirmationTime;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    private LocalDateTime expirationTime;

    public ConfirmationToken(){

    }

    public ConfirmationToken(String token, LocalDateTime creationTime, LocalDateTime confirmationTime,
                             LocalDateTime expirationTime){
        this.token = token;
        this.creationTime = creationTime;
        this.confirmationTime = confirmationTime;
        this.expirationTime = expirationTime;
    }

    //TODO override hashCode and equals
}
