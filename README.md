# 📚 Library Management System

A backend REST API for managing books in a library, built using **Java, Spring Boot, Spring Data JPA, and MySQL**.

This project is being developed step-by-step to understand how production-style Spring Boot applications are designed, from basic CRUD operations to DTOs, validation, exception handling, and business logic.

---

## 🚀 Tech Stack

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Maven**
- **Bean Validation**
- **Postman**
- **DBeaver**

---

## ✨ Current Features

### 📖 Book Management

- Create a book
- Get all books
- Get a book by ID
- Update a book
- Soft delete a book
- Automatically manage book availability status

### 🔐 DTO-Based API

The API uses separate DTOs instead of exposing the `Book` entity directly.

#### Request DTOs

- `CreateBookRequestDto`
- `UpdateBookRequestDto`

#### Response DTO

- `BookResponseDto`

This provides better control over which fields clients can create or update.

### ✅ Request Validation

Bean Validation has been implemented using:

- `@NotBlank`
- `@NotNull`
- `@Min`
- `@Size`
- `@Pattern`

Examples of validation rules:

- ISBN must follow the `978` / `979` ISBN-13 format
- Required text fields cannot be blank
- Price must be at least ₹1
- Total copies must be at least 1
- Text fields have maximum length restrictions

### 🗑️ Soft Delete

Books are not physically removed from the database.

Instead, the `isDeleted` flag is set to `true`.

Normal queries only return books where:

`isDeleted = false`

### 📊 Book Availability

The system maintains:

- Total copies
- Available copies
- Book status

Current statuses:

- `AVAILABLE`
- `OUT_OF_STOCK`

When a book is created, its available copies are initialized from the total copies and the status is calculated automatically.

---

## 🏗️ Architecture

The application follows a layered architecture:

```text
Client
  │
  ▼
Controller
  │
  ▼
Service
  │
  ▼
Repository
  │
  ▼
MySQL