package com.denisov.anything.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//TODO: comments
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.denisov.anything.security.securityconfig"})
@ComponentScan(basePackages = {"com.denisov.anything.authservice.user"})
@ComponentScan(basePackages = {"com.denisov.anything.authservice.token"})
@ComponentScan(basePackages = {"com.denisov.anything.products"})
@ComponentScan(basePackages = {"com.denisov.anything.recepies"})
@ComponentScan(basePackages = {"com.denisov.anything.steps"})
@ComponentScan(basePackages = {"com.denisov.anything.productset"})

@EnableJpaRepositories({"com.denisov.anything.recipes","com.denisov.anything.products",
        "com.denisov.anything.authservice.confirmation","com.denisov.anything.authservice.user",
         "com.denisov.anything.recepies", "com.denisov.anything.productset","com.denisov.anything.steps"})
@EntityScan({"com.denisov.anything.products","com.denisov.anything.recipes","com.denisov.anything."})
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class })

public class Runner {
    public static void main(String[] args){
        SpringApplication.run(Runner.class, args);
    }
}
