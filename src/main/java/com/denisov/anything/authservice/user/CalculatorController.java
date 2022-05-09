package com.denisov.anything.authservice.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("calc")
public class CalculatorController {

    @GetMapping("/sum")
    public String sum(@RequestParam(name="x")double param1, @RequestParam(name="y")double param2){
        return ""+(param1 + param2);
    }

    @GetMapping("/subtract")
    public String sub(@RequestParam(name="x")double param1, @RequestParam(name="y")double param2){
        return ""+(param1 - param2);
    }
}
