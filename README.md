# Getting Started

Welcome to my Api Rest Exercise

Project created using SrpingBoot3 and Java 21

Is possible to compile project, download dependencies and run Tests executing in terminal this command:
./mvnw install

In order to run this Application, please execute next command:
./mvnw spring-boot:run

Level traces is DEBUG set by appliction.properties file
This application is listening at 8080 port

Exist two sql files to set data required for the exercise.
If you compile this project this sql files are used to test the application.
If you run this project will exist this initial inserts like initial data.

### Swagger
 url => http://localhost:8080/swagger-ui/index.html

### H2 Console
url => http://localhost:8080/h2-ui/

In order to correct connection verify (if is your first connection) id the field "JDBC URL" contains this exactly => "jdbc:h2:mem:testdb" (omitting quotes)
because this project is running with embedded in-memory database 
