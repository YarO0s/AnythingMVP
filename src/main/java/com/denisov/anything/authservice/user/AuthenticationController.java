package com.denisov.anything.authservice.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//TODO: logic where authorized asks for authentication
@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public String authenticate(@RequestParam(name = "username")String username,
                               @RequestParam(name = "password")String password){
        return authService.authenticate(username, password);
    }
}
