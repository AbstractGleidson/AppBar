package com.serverBar.serverBar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {

    @GetMapping("/")
    public String helloWorld()
    {
        return "Application for managing a bar";
    }
}
