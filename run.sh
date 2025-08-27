#!/bin/bash

# Spring Boot Application Runner Script

echo "🚀 Spring Boot Bubble Sort Application"
echo "======================================"

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed!"
    echo "Please install Maven first:"
    echo "  brew install maven"
    echo "  or download from: https://maven.apache.org/download.cgi"
    exit 1
fi

# Check if Java 17+ is available
java_version=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$java_version" -lt 17 ]; then
    echo "❌ Java 17 or later is required!"
    echo "Current Java version: $(java -version 2>&1 | head -n 1)"
    exit 1
fi

echo "✅ Maven and Java are available"
echo ""

# Menu options
echo "Choose an option:"
echo "1) Run the application"
echo "2) Build the project"
echo "3) Run tests"
echo "4) Clean and build"
echo "5) Exit"

read -p "Enter your choice (1-5): " choice

case $choice in
    1)
        echo "🌐 Starting Spring Boot application..."
        echo "Application will be available at: http://localhost:8080"
        echo "API endpoints:"
        echo "  - Home:  GET  http://localhost:8080/"
        echo "  - Sort:  GET  http://localhost:8080/api/sort?numbers=64,34,25,12,22,11,90,88,76,45"
        echo "  - Sort:  POST http://localhost:8080/api/sort"
        echo "            Body: {\"numbers\": [64,34,25,12,22,11,90,88,76,45]}"
        echo "  - Health: GET  http://localhost:8080/api/health"
        echo "  - Demo:  http://localhost:8080/index.html"
        echo "Press Ctrl+C to stop the application"
        echo ""
        mvn spring-boot:run
        ;;
    2)
        echo "🔨 Building the project..."
        mvn clean compile
        ;;
    3)
        echo "🧪 Running tests..."
        mvn test
        ;;
    4)
        echo "🧹 Cleaning and building..."
        mvn clean compile
        ;;
    5)
        echo "👋 Goodbye!"
        exit 0
        ;;
    *)
        echo "❌ Invalid choice. Please run the script again."
        exit 1
        ;;
esac
