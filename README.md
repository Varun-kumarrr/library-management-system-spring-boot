# 📚 Library Management System - Spring Boot

A RESTful Library Management System built using **Spring Boot**, **Spring Data JPA**, and **MySQL**. This project demonstrates industry-standard layered architecture and CRUD operations with **Soft Delete** support.

---

## 🚀 Features

- Add a new book
- Get all available books
- Get a book by ID
- Update book details
- Soft delete books
- Spring Data JPA custom finder methods
- Layered Architecture (Controller → Service → Repository)
- MySQL database integration

---

## 🛠️ Tech Stack

- Java 25
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman

---

## 📂 Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── resources
 └── LibraryManagementSystemApplication.java
```

---

## 📌 REST APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/books` | Create a new book |
| GET | `/api/books` | Get all active books |
| GET | `/api/books/{id}` | Get a book by ID |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Soft delete a book |

---

## 📖 Book Entity

```java
Book
├── id
├── isbn
├── title
├── author
├── publisher
├── category
├── description
├── price
├── totalCopies
├── availableCopies
├── status
└── isDeleted
```

---

## 💡 Concepts Covered

- REST API Development
- CRUD Operations
- Spring Boot Annotations
- Dependency Injection
- Constructor Injection
- Layered Architecture
- Spring Data JPA
- Custom Query Methods
- Soft Delete
- Business Logic in Service Layer
- ResponseEntity
- Optional
- MySQL Integration

---

## ▶️ Running the Project

1. Clone the repository

```bash
git clone https://github.com/Varun-kumarrr/library-management-system-spring-boot.git
```

2. Open the project in IntelliJ IDEA.

3. Configure MySQL in `application.properties`.

4. Run the Spring Boot application.

5. Test the APIs using Postman.

---

## 📅 Upcoming Features

- Bean Validation
- Global Exception Handling
- DTO Pattern
- Pagination & Sorting
- Swagger/OpenAPI
- Book Issue & Return
- Authentication & Authorization (Spring Security + JWT)

---

## 👨‍💻 Author

**Varun Kumar**

If you found this project helpful, consider giving it a ⭐ on GitHub.
