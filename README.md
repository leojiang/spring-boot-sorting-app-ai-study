# Spring Boot Bubble Sort Application

This is a Spring Boot application that implements a bubble sort algorithm with a RESTful web interface, configured to use Maven for building and dependency management.

## Prerequisites

You need to have Java 17 or later and Maven installed on your system.

### Installing Maven

#### Option 1: Using Homebrew (macOS)
```bash
brew install maven
```

#### Option 2: Manual Installation
1. Download Maven from [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
2. Extract the archive to a directory (e.g., `/opt/maven`)
3. Add Maven to your PATH:
   ```bash
   export PATH=$PATH:/opt/maven/bin
   ```

## Project Structure

```
tryout/
├── pom.xml                 # Maven project configuration with Spring Boot
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── cursor/
│       │           └── tryout/
│       │               ├── Main.java           # Spring Boot application startup
│       │               ├── SortController.java # REST API controller for sorting
│       │               ├── SortService.java    # Service layer for sorting logic
│       │               └── HomeController.java # Home page controller
│       └── resources/
│           ├── application.properties          # Spring Boot configuration
│           ├── banner.txt                      # Custom application banner
│           └── static/
│               └── index.html                  # Interactive demo page for the API
└── README.md               # This file
```

## Building and Running

### Compile the project
```bash
mvn clean compile
```

### Run the Spring Boot application
```bash
mvn spring-boot:run
```

### Create a runnable JAR
```bash
mvn clean package
```

### Run the JAR file
```bash
java -jar target/tryout-1.0-SNAPSHOT.jar
```

### Run tests
```bash
mvn test
```

The project includes comprehensive tests for:
- Spring Boot application context loading
- SortService functionality (bubble sort algorithm)
- SortController API endpoints
- Edge cases (empty arrays, single elements, null arrays)
- Request/Response DTOs

## API Endpoints

Once the application is running on port 8080, you can access:

- **Home**: `GET http://localhost:8080/`
- **Health Check**: `GET http://localhost:8080/api/health`
- **Sort Array (GET)**: `GET http://localhost:8080/api/sort?numbers=64,34,25,12,22,11,90,88,76,45`
- **Sort Array (POST)**: `POST http://localhost:8080/api/sort`

### Example API Usage

#### Sort an array using GET request (recommended for simple cases):
```bash
curl "http://localhost:8080/api/sort?numbers=64,34,25,12,22,11,90,88,76,45"
```

#### Sort an array using POST request:
```bash
curl -X POST http://localhost:8080/api/sort \
  -H "Content-Type: application/json" \
  -d '{"numbers": [64, 34, 25, 12, 22, 11, 90, 88, 76, 45]}'
```

#### Response:
```json
{
  "original": [64, 34, 25, 12, 22, 11, 90, 88, 76, 45],
  "sorted": [11, 12, 22, 25, 34, 45, 64, 76, 88, 90]
}
```

## What the Application Does

The Spring Boot application provides:
1. A web interface for the bubble sort algorithm
2. REST API endpoints for sorting arrays (both GET and POST)
3. Service layer architecture separating business logic from controllers
4. Health check and monitoring capabilities
5. Console output showing the sorting process
6. Interactive HTML demo page at http://localhost:8080/index.html

## Architecture

The application follows a layered architecture:
- **Main Class** (`Main.java`): Spring Boot application startup and configuration
- **Controller Layer**: 
  - `SortController.java`: Handles sorting API endpoints (`/api/sort`, `/api/health`)
  - `HomeController.java`: Handles home page (`/`)
- **Service Layer** (`SortService.java`): Contains the business logic for sorting algorithms
- **Model Layer**: Request/Response DTOs for data transfer

## Spring Boot Configuration

The application is configured with:
- Spring Boot 3.2.3 (latest stable version)
- Spring Web starter for REST endpoints
- Java 17 as the target version
- Custom application banner
- Configurable server port (default: 8080)

## Troubleshooting

If you get "command not found: mvn", make sure Maven is installed and added to your PATH.

If you get Java version errors, make sure you have Java 17 or later installed:
```bash
java -version
```

If the application fails to start, check that port 8080 is available or change it in `application.properties`.
