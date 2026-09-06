# Queue Forge

Queue Forge is a Spring Boot REST API for managing a repair-service queue. It models customers, devices, technicians, and repair jobs, with CRUD operations currently available for customers and technicians.

## Tech Stack

- Java 25
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA / Hibernate
- PostgreSQL
- Lombok
- Maven

## Prerequisites

- Java 25 or later
- PostgreSQL
- A PostgreSQL database named `queue`
- A PostgreSQL user with permission to access the database

## Configuration

The default database configuration is in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/queue
spring.datasource.username=postgres
spring.datasource.password=secret
```

Create the database before starting the application:

```sql
CREATE DATABASE queue;
```

Update the username, password, or JDBC URL in `application.properties` to match your local PostgreSQL setup. Hibernate is configured to update the schema automatically during development.

## Running the Application

From the project root, use the Maven wrapper:

```bash
./mvnw spring-boot:run
```

The API starts on the default Spring Boot port:

```text
http://localhost:8080
```

To build and run the tests:

```bash
./mvnw test
```

To create a packaged JAR:

```bash
./mvnw clean package
```

## API Endpoints

### Customers

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/customer` | List all customers |
| `GET` | `/customer/{id}` | Get a customer by ID |
| `POST` | `/customer` | Create a customer |
| `PUT` | `/customer/{id}` | Update an existing customer |
| `DELETE` | `/customer/{id}` | Delete a customer |

Example customer request:

```json
{
  "customer_name": "Alex Johnson"
}
```

### Technicians

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/technician` | List all technicians |
| `GET` | `/technician/{id}` | Get a technician by ID |
| `POST` | `/technician` | Create a technician |
| `PUT` | `/technician/{id}` | Update an existing technician |
| `DELETE` | `/technician/{id}` | Delete a technician |

Example technician request:

```json
{
  "technicianName": "Morgan Lee"
}
```

Requests and responses use JSON. A lookup for a missing customer or technician returns `404 Not Found`.

## Domain Model

- `Customers` can own multiple `Devices`.
- `Technicians` can be associated with a device and repair job.
- `Devices` can be associated with a customer, technician, and repair job.
- `RepairJob` represents work assigned to a device and technician.
- Available status enums include `AVAILABLE`, `NOT_AVAILABLE`, `PENDING`, `TECHNICIAN_ASSIGNED`, `IN_PROGRESS`, and `SUCCESSFULLY_REPAIRED`.

Device and repair-job persistence models are present, but REST controllers for those resources are not currently exposed.

## Project Structure

```text
src/main/java/com/jashan/queue_forge/
├── controller/   REST controllers
├── enums/        Domain status and priority enums
├── models/       JPA entities
└── Repository/   Spring Data repositories
```

## Development Notes

SQL logging is enabled in the default configuration. Do not use the sample database password in a shared or production environment; provide credentials through environment-specific configuration instead.