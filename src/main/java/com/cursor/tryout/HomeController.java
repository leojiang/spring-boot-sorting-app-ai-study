package com.cursor.tryout;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home controller providing basic information about the application.
 */
@RestController
public class HomeController {

    /**
     * Home endpoint providing application information and available endpoints.
     * @return welcome message with endpoint information
     */
    @GetMapping("/")
    public String home() {
        return "Welcome to Bubble Sort Spring Boot Application!\n\n"
                + "Available endpoints:\n"
                + "- GET  /api/sort?numbers=64,34,25,12,22,11,90,88,76,45\n"
                + "- POST /api/sort\n"
                + "- GET  /api/health\n"
                + "- GET  /\n\n"
                + "The application provides a REST API for sorting arrays "
                + "using bubble sort algorithm.";
    }
}
