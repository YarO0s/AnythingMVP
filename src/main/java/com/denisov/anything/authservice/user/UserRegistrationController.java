package com.denisov.anything.authservice.user;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth", produces = "application/json")
public class UserRegistrationController {
    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(UserRegistrationService userRegistrationService){
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/new")
    public String registerUser(@RequestParam("name")String name,
                               @RequestParam("email")String email,
                               @RequestParam("password")String password){
        User user = new User(name, email, password);
        return new JSONObject().put("result", ""+ userRegistrationService.registerUser(user)).toString();
    }

    @PostMapping("/confirm")
    public String confirmUser(@RequestParam("token")String token){
        return userRegistrationService.confirmUser(token);
    }
}
