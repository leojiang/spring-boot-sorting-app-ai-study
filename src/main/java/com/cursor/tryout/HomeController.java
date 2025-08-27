package com.cursor.tryout;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "Welcome to Bubble Sort Spring Boot Application!\n\n" +
               "Available endpoints:\n" +
               "- GET  /api/sort?numbers=64,34,25,12,22,11,90,88,76,45\n" +
               "- POST /api/sort\n" +
               "- GET  /api/health\n" +
               "- Demo: /index.html";
    }
}
