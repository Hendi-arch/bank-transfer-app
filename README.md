# Bank Transfer Application Sample

The Bank Transfer Application Sample enables users to initiate fund transfers between accounts and internal banks seamlessly.

## Supported Features

- **Authentication & Authorization**: Utilizes JWT (JSON Web Tokens) for secure authentication and RBAC (Role-Based Access Control) for controlling user access to resources.
- **User Management**: Enables users to perform actions such as logging in, signing up, updating their information, and searching for other users.
- **User Token Management**: Facilitates the creation, search, and deletion of user tokens for enhanced security and session management.
- **Transaction Management**: Allows users to initiate fund transfers between accounts and internal banks, with support for creating and searching transactions.
- **User Role Management**: Provides functionality for creating, searching, and deleting user roles, empowering administrators to manage user permissions effectively.

## Installation

To run the application locally, follow these steps:

1. Install Java and Maven.
2. Set up the database according to the configuration specified in `application.properties`.
3. Clone this repository.
4. Navigate to the project directory.
5. Run `mvn spring-boot:run` to start the application.

The application will start running on **<http://localhost:3333>**.

Alternatively, you can run the application using Docker. Here's how:

### Running with Docker

Ensure you have Docker installed on your system. Then, follow these steps:

1. Use the existing `Dockerfile`, `docker-compose.yml`, and environment files `app.env` and `db.env` available in the project directory.

2. Run `docker-compose up -d` to start the application and the database containers.

This will build and run the application on **<http://localhost:3333>** along with the PostgreSQL database using Docker containers. Adjust the configurations in the environment files as needed for your setup.

## Usage

Once the application is running, you can interact with it through the following methods:

- **API Requests:** Use tools like cURL or Postman to make API requests to the exposed endpoints. To quickly get started with Postman, you can import the provided Postman collection by uploading the `postman-collection.json` file located in the project directory.

## API Endpoints

The application exposes the following API endpoints:

- **POST** `/users/login`: Endpoint for user login.
- **POST** `/users/user`: Endpoint for user signup.
- **PUT** `/users/{id}`: Endpoint for updating user information.
- **GET** `/users/{id}`: Endpoint for searching users.
- **GET** `/users/all`: Endpoint for getting all users.
- **GET** `/usertokens/{authToken}`: Endpoint for searching user tokens.
- **DELETE** `/usertokens/{authToken}`: Endpoint for deleting user tokens.
- **POST** `/transactions/{senderId}/transfer`: Endpoint for creating transactions.
- **GET** `/transactions/{id}`: Endpoint for searching transactions.
- **GET** `/userroles/{id}`: Endpoint for searching user role.
- **GET** `/userroles/all`: Endpoint for getting all user roles.

For detailed information on each endpoint, refer to the [API documentation.](https://app.swaggerhub.com/apis-docs/HENDINOFIANSYAH11/bank-transfer-application-api/1.3.0)

## Configuration

The application can be configured using the `application.properties` file. Configure database connection settings, external service URLs, and other options as needed.

## App Architecture

The app is composed by three main layers, they are: Infrastructure, Use case and Entity. In which follows a rigid hierarchy between the layers - the codes of the innermost layers do not know those of the outermost.

<p align="center">
  <img src="https://github.com/vinimrs/spring-boot-clean-architecture/assets/92659173/b2093822-8aa2-4606-af36-1d2410a9b27f" />
  <p align="center">
      <i>Spring boot architecture with clean arhcitecture</i>
   </p>
</p>

For more info about this, read this tutorial:

- [Clean Architecture with Spring Boot](https://www.linkedin.com/pulse/clean-architecture-spring-boot-good-idea-vin%C3%ADcius-romualdo-dzwlf/)

### Entity Layer

The Entity layer represents the models of the system domain, containing all the models and the contracts of how the data of the models should be manipulated in relation to the database.

### Infrastructure Layer

The Infrastructure layer is a layer that represents all the code that depends on the framework, the controllers, the JPA repositories, the schemas of the domain models, and the implementations of lower-tier gateways.

### UseCase Layer

The Use Case layer contains the system use cases, which can be made parallel to the services of a conventional Spring Boot application, and the contracts of the DTOs (Data Transfer Objets) which represent how the data must transit between the inner layers of the application and the others.

## Packages in use

- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Validation](https://spring.io/guides/gs/validating-form-input)
- [Spring Web](https://spring.io/guides/gs/spring-boot)
- [Spring Test](https://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/html/boot-features-testing.html)
- [Test Containers](https://testcontainers.com/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Lombok](https://projectlombok.org/)

## Testing

The application includes automated tests. Run `mvn test` to execute the tests and verify the functionality.
