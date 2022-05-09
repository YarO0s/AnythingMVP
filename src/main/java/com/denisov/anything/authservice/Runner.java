package com.denisov.anything.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//TODO: comments
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.denisov.anything.security.securityconfig"})
@ComponentScan(basePackages = {"com.denisov.anything.authservice.user"})
@ComponentScan(basePackages = {"com.denisov.anything.authservice.token"})
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class })

public class Runner {
    public static void main(String[] args){
        SpringApplication.run(Runner.class, args);
    }
}
