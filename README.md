👩‍💼 **HR Employee Management App**

A RESTful Spring Boot application with a layered architecture that supports small HR teams by providing CRUD operations to simplify employee data management.

📌 **Features**

* CRUD operations for employees
* Address, salary, and gender data management
* PostgreSQL persistence with Hibernate
* Schema versioning with Flyway
* Clean DTO ↔ Entity mapping using MapStruct
* Unit and integration tests with JUnit & Mockito

The main endpoints include:
```
- `GET /api/users` – get all employees
- `GET /api/users/{id}` – get employee by ID
- `POST /api/users` – create new employee
- `PUT /api/users/{id}` – update employee
- `DELETE /api/users` – delete all employees
```

🏗 **Architecture**

The project follows a Repository–Service–Controller architecture, ensuring clean separation of concerns:

* Controller: Handles HTTP requests and responses (e.g., EmployeeController.java).
* DTO (Data Transfer Objects): Defines request/response payloads and supports clean mapping (e.g., EmployeeDto.java).
* Domain (Entities): Represents the core data model mapped to the database (e.g., Employee.java, Address.java).
* Repository: Provides persistence and database access using Spring Data JPA (e.g., EmployeeRepository.java).
* Service: Contains business logic and orchestrates operations (e.g., EmployeeService.java).
* Util: Helper classes and shared functionality (e.g., exception handling, mappers).

This layered architecture improves maintainability, testability, and scalability.

🛠️ **Technologies**
+ Java 11 
+ Spring Boot 
+ Spring Data JPA (Hibernate) 
+ PostgreSQL
+ Flyway (DB migrations)
+ MapStruct (DTO ↔ Entity mapping)
+ JUnit 5 & Mockito (testing)
+ Maven

⚙️ **Build & Run**

The project is built with Maven.

Build

```
mvn clean install
```
Run

```
mvn spring-boot:run
```

Or run the main class:
```
mvn exec:java -Dexec.mainClass="com.example.demowithtests.DemoWithTestsApplication"
```

🗄️ **Database Configuration**

Make sure PostgreSQL is installed and running.

The application expects a database named **employee**:  

```
CREATE DATABASE employee;
```

Connection details are defined in src/main/resources/application.yml

Flyway will automatically run migrations on startup.
