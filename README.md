# Smart Village Management System

## Project Overview

Smart Village Management System is a full-stack web application designed to digitize and streamline village administration services. It enables efficient management of users, announcements, complaints, and government schemes through a secure and scalable backend system.

---

## Project Status

Backend: Completed and integration-ready
Frontend: In progress

---

## Features

### Authentication and Security

* User registration and login
* JWT-based authentication
* Role-based access control (ADMIN, SARPANCH, CITIZEN)
* Password encryption using BCrypt

---

### User Management

* View and update user profiles
* Admin functionalities:

 
  * Activate or block users
  * View all users

---

### Announcements

* Public users can view active announcements
* Admin and Sarpanch can:

  * Create, update, and delete announcements
  * Filter by type and status

---

### Complaints Management

* Citizens can:

  * Raise complaints
  * View and manage their complaints
* Admin and Sarpanch can:

  * View all complaints
  * Assign handlers
  * Update complaint status

---

### Government Schemes

* Public users can:

  * View active schemes
  * Search and filter schemes
* Admin can:

  * Create, update, and deactivate schemes

---

### Scheme Applications

* Citizens can apply for schemes
* Track application status
* Admin and Sarpanch can:

  * Review applications
  * Approve or reject applications

---

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* Hibernate / JPA

### Database

* MySQL

### Tools

* Maven
* Postman
* Git and GitHub

---

## Project Structure

smart-village-management/
│
├── smart-village-backend/
│   ├── controller/
│   ├── service/
│   ├── entity/
│   ├── dto/
│   ├── repository/
│   ├── security/
│   ├── config/
│   ├── exceptions/
│   └── resources/
│
└── README.md

---

## Setup Instructions

### Prerequisites

* Java 17 or higher
* MySQL
* Maven

---

### Database Setup

Create database:

CREATE DATABASE smart_village_db;

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/smart_village_db
spring.datasource.username=root
spring.datasource.password=root

---

### Run Backend

cd smart-village-backend
./mvnw spring-boot:run

Server will start at:
http://localhost:8080

---

## API Base URL

http://localhost:8080/api

---

## Testing APIs

Use Postman or Insomnia.

Authentication flow:

1. Register user using /api/auth/register
2. Login using /api/auth/login
3. Copy JWT token
4. Add token in headers:

Authorization: Bearer <your_token>

---

## Test Credentials

Role: ADMIN
Email: [admin@test.com](mailto:admin@test.com)
Password: password123

Role: SARPANCH
Email: [sarpanch@test.com](mailto:sarpanch@test.com)
Password: password123

Role: CITIZEN
Email: [citizen@test.com](mailto:citizen@test.com)
Password: password123

---

## Database Tables

* users
* announcements
* complaints
* schemes
* scheme_applications
* eligibility

---

## Security Features

* JWT authentication
* Role-based authorization
* Password encryption (BCrypt)
* Global exception handling
* Input validation

---

## Frontend Integration

* CORS enabled for:

  * http://localhost:3000
  * http://localhost:5173

* Use JWT token for accessing protected APIs

---

## Future Enhancements

* File upload support
* Notification system
* Unit testing
* Swagger API documentation
* Deployment

---

## Author

Ankita
Final Year Project – Smart Village Management System

---

## Repository

https://github.com/ankita28sep/smart-village-management
