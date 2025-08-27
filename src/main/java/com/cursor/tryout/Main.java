package com.cursor.tryout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Spring Boot sorting application.
 */
@SpringBootApplication
public class Main {

    /**
     * Public constructor for Spring Boot.
     */
    public Main() {
        // Spring Boot will instantiate this class
    }

    /**
     * Main method to start the Spring Boot application.
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
