# Dumping Excel Data to Database

This Spring Boot application demonstrates how to upload Excel data and store it into a PostgreSQL database using Apache POI and Spring Data JPA.

## Endpoint Base URL
- Base URL: [http://localhost:6000](http://localhost:6000)

## Endpoints

### Upload Excel File
- **URL:** `/product/upload`
    - Example: [http://localhost:6000/product/upload](http://localhost:6000/product/upload)
    - **Method:** POST
    - **Description:** Upload an Excel file containing product data and store it into the database.
    - **Request Body:** Form-data with `file` parameter containing the Excel file.

### Get All Products
- **URL:** `/product`
    - Example: [http://localhost:6000/product](http://localhost:6000/product)
    - **Method:** GET
    - **Description:** Retrieve all products stored in the database.

## Prerequisites

Before running the application, ensure you have the following installed:

- Java Development Kit (JDK) 17 or higher
- Apache Maven (for building the project)
- PostgreSQL database server (with database/schema created)

## Configuration

Ensure to configure your PostgreSQL database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update

spring.servlet.multipart.max-file-size=3MB
spring.servlet.multipart.max-request-size=3MB
