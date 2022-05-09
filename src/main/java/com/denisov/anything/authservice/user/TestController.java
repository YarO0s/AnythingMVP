package com.denisov.anything.authservice.user;

import com.denisov.anything.security.securityconfig.JWTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {
    private final UserRepository userRepository;

    @Value("${auth.jwt.secret}")
    private String secretKey;

    public TestController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam()String p){
        return "hello " + p;
    }
}
