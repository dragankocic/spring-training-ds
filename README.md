# Spring Training Application

This is a Spring Boot application developed by dragankocic. The application is designed to manage teams and players, demonstrating both API and MVC structure using the same services. The project connects to the BallDontLie API for seeding information in the H2 database on startup. Validations and their handling are implemented. The architecture of the project is Package by Feature.

## Technologies Used

- Java
- SQL
- Spring Boot
- Maven
- Flyway

## Project Structure

The project is divided into several modules:

- `config` : This module contains the configuration classes for the application.
- `teams`: This module is responsible for managing teams.
- `player`: This module is responsible for managing players.

Examples of integration and unit tests are set in the project.

## Setup

To run this project, you need to have Java and Maven installed on your machine.

1. Clone the repository
2. Navigate to the project directory
3. Add the appropriate BallDontLie API key in the `application.properties` file
4. Run `mvn clean install` to build the project
5. Run `mvn spring-boot:run` to start the application

## Database Migration

Flyway is implemented for database migration. The migration files can be found in the `src/main/resources/db/migration` directory.

## Testing

To run the tests, use the following command:

```bash
mvn test
```

## Error Handling

The project implements the RFC 7807 standard for exception handling using ProblemDetail.