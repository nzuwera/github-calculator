[![Build and Deploy](https://github.com/nzuwera/github-calculator/actions/workflows/build.yml/badge.svg)](https://github.com/nzuwera/github-calculator/actions/workflows/build.yml) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=nzuwera_github-calculator&metric=alert_status)](https://sonarcloud.io/dashboard?id=nzuwera_github-calculator)
# Calculator REST API

A simple calculator REST API built with Spring Boot that supports basic arithmetic operations.

## Technologies Used

- Java 21
- Spring Boot 3.5.4
- Thymeleaf for web UI
- OpenAPI/Swagger for API documentation
- Docker for containerization
- JUnit 5 for testing
- Maven for build management

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Docker (optional, for containerization)

## Getting Started

### Clone the repository
```sh
git clone https://github.com/nzuwera/github-calculator.git
cd github-calculator
```

### Build the application
```sh
mvn clean package
```

### Run the application
```sh
java -jar target/github-calculator-1.0.jar
```

The application will start on port 8080. You can access:
- Web UI: http://localhost:8080/
- API Documentation: http://localhost:8080/swagger-ui/index.html

## Docker Support

### Build Docker image
```sh
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=nzuwera/github-calculator
```

### Run Docker container
```sh
docker run -p 8080:8080 nzuwera/github-calculator
```

## API Usage

The calculator supports the following arithmetic operations:

| Operation | Supported Values |
| --------- | ------ |
| Addition | ADD |
| Subtraction | SUBTRACT |
| Multiplication | MULTIPLY |
| Division | DIVIDE |

### REST Endpoints

#### 1. Modern API (Recommended)
```
POST /calculator/{operation}
```

Example:
```sh
curl -X POST 'http://localhost:8080/calculator/ADD' \
  -H 'Content-Type: application/json' \
  -d '[1, 2, 3, 4]'
```

Response:
```
1 2 3 4 = 10
```

#### 2. Legacy API (For backward compatibility)
```
GET /calculator/{operation}/{a}/{b}
```

Example:
```sh
curl 'http://localhost:8080/calculator/A/5/3' -i -X GET
```

Response:
```
HTTP/1.1 200 OK
Content-Type: text/plain;charset=UTF-8
Content-Length: 9

5 3 = 8
```

## Testing

Run the tests with:
```sh
mvn test
```

## License

This project is licensed under the MIT License.
