Multiple Databases Project
This project is an application that demonstrates how to configure and work with multiple databases using Spring Boot, JPA, and MySQL. It includes two separate databases: book and user.

Technologies Used
Java 17
Spring Boot
Spring Data JPA
MySQL
Lombok
Prerequisites
Java Development Kit (JDK) 17
MySQL Server
Maven

Setup
Clone the repository:

Create the databases in MySQL
CREATE DATABASE book;
CREATE DATABASE user;

Update the database connection properties in src/main/resources/application.properties with your MySQL credentials:

book.datasource.jdbcurl=jdbc:mysql://localhost:3306/book?createDatabaseIfNotExist=true&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
book.datasource.username=your_mysql_username
book.datasource.password=your_mysql_password

user.datasource.jdbcurl=jdbc:mysql://localhost:3306/user?createDatabaseIfNotExist=true&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
user.datasource.username=your_mysql_username
user.datasource.password=your_mysql_password

Build the project:
mvn clean install

Run the application:
mvn spring-boot:run
