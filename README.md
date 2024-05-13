# ChaTop API

## Description

The ChaTop API operates as the server for the ChaTop application.
Built with Spring Boot 3 and Java 21, it integrates Spring-doc OpenAPI and Swagger UI to provide thorough documentation.  

A Front-End application using this API is here :  
- https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring

## Features

The ChaTop API includes the following principal features:

- User Authentication with **JWT**
- User registration and login
- Rental creation with upload picture
- Rental display
- Rental update
- Sending Messages

## Installation

1. Clone this repository.
2. Install JDK 21 (Java Development Kit) on your local machine.
3. Install Maven locally.
4. Install MariaDB and create a database for the application.
5. Configure the necessary environment variables in your system or within your IDE before running the application.

### Necessary SQL database configuration

You can use this SQL script file for creating the tables in the database :

- [SQL Script](src/main/resources/scripts/database/schema.sql)

### properties settings

Before launching the application, please configure the following properties in the application.properties configuration file:

- spring.datasource.url: The JDBC URL of your database. For example: jdbc:mysql://localhost:3306/my_database.
- spring.datasource.username: The username of your database. For example: db_user.
- spring.datasource.password: Your database password. For example: password_db.
- security.jwt.secret-key: The secret key used to sign JWT tokens. Make sure you choose a secure and complex key. For example: my_secret_key12345578787848.
- security.jwt.expiration-time: The expiration time of JWT tokens, in milliseconds. For example: 360000.
- image.upload-dir: The location where images will be stored. Make sure this path is correct and accessible.

## Executing the application

### Launch the application by executing the JAR file

- Generate a JAR in the root folder of the project by executing:
  ```bash
  mvn clean install

- Launch the application by executing
  ```bash
  spring-boot:run

## Conducting tests using Postman

You can use [Postman](https://www.postman.com/) to test the ChaTop API.  
Download the Postman collection and environment script:

- [ChaTop API Postman Collection](src/main/resources/scripts/postman/rental.postman_collection.json)  
- Note: The only two unauthenticated endpoints are `/api/auth/register` and `/api/auth/login`  
- It is recommended to create a first user by sending a POST request to the `/api/auth/register` endpoint and retrieve the returned `JWT` token to authenticate requests. 

## Conducting tests using Swagger UI
- Run the application ChaTop API
- Access the Swagger UI documentation at http://localhost:3001/api/swagger-ui/index.html
- Register a user by sending a POST request to the `/api/auth/register` endpoint
- Login by sending a POST request to the `/api/auth/login` endpoint
- Copy the JWT token from the response
- Click on the `Authorize` button on the top right corner of the Swagger UI page
- Paste the JWT token in the `Value` field and click on the `Authorize` button
- `You can now test the API endpoints`

## The following technologies are employed in the development of the ChaTop API application:

- **Java:** Version 21
- **Spring Boot:** Version 3.2.5
- **Spring Security:** Starter for securing applications
- **Spring Web:** Starter for building web applications
- **MariaDB Connector/J:** Runtime dependency for MariaDB database connectivity
- **Project Lombok:** Used for reducing boilerplate code
- **JWT (JSON Web Token):** Java library for working with JSON Web Tokens
- **Spring Data JPA:** Starter for using Spring Data JPA with Hibernate
- **Springdoc OpenAPI:** Starter for OpenAPI and Swagger UI documentation
  - Version 2.5.0
