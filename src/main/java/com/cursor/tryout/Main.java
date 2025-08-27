package com.cursor.tryout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Spring Boot sorting application.
 */
@SpringBootApplication
public final class Main {

    /**
     * Private constructor to prevent instantiation.
     */
    private Main() {
        // Utility class - no instantiation needed
    }

    /**
     * Main method to start the Spring Boot application.
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
