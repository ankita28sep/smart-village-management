# Frontend Integration Guide

## Overview

This document provides complete instructions for integrating a frontend application (React, Vue, etc.) with the Smart Village Management System backend.

The backend exposes REST APIs secured with JWT authentication and role-based access control.

---

## API Base URL

http://localhost:8080/api

All API endpoints are prefixed with `/api`.

Example:
http://localhost:8080/api/auth/login

---

## Authentication Flow (Step-by-Step)

### Step 1: Register User

Endpoint:
POST /api/auth/register

Request Body:
{
"name": "Test User",
"email": "[user@test.com](mailto:user@test.com)",
"password": "password123",
"role": "CITIZEN"
}

---

### Step 2: Login User

Endpoint:
POST /api/auth/login

Request Body:
{
"email": "[user@test.com](mailto:user@test.com)",
"password": "password123"
}

Response:
{
"token": "JWT_TOKEN_HERE",
"type": "Bearer",
"role": "CITIZEN"
}

---

### Step 3: Store Token

Store JWT token in:

* localStorage (recommended)
  OR
* sessionStorage

Example:
localStorage.setItem("token", response.token);

---

### Step 4: Use Token in Requests

Include token in Authorization header for all protected APIs:

Authorization: Bearer <your_token>

---

## API Usage Examples

### 1. Login API

POST /api/auth/login

fetch("http://localhost:8080/api/auth/login", {
method: "POST",
headers: {
"Content-Type": "application/json"
},
body: JSON.stringify({
email: "[admin@test.com](mailto:admin@test.com)",
password: "password123"
})
})

---

### 2. Get User Profile (Protected)

GET /api/users/1

Headers:
Authorization: Bearer <your_token>

---

### 3. Get Active Schemes (Public)

GET /api/schemes/active

No authentication required.

---

### 4. Create Complaint (Protected - CITIZEN)

POST /api/complaints

Headers:
Authorization: Bearer <your_token>

Body:
{
"title": "Water issue",
"description": "No water supply in my area"
}

---

### 5. Get My Complaints

GET /api/complaints/my

Headers:
Authorization: Bearer <your_token>

---

## Role-Based Access Summary

| Role     | Access                           |
| -------- | -------------------------------- |
| ADMIN    | Full system access               |
| SARPANCH | Announcements + Complaints       |
| CITIZEN  | Complaints + Scheme Applications |

Frontend should:

* Show/hide UI based on role
* Restrict access to certain pages

---

## Frontend Setup (React - Vite)

### Create Project

npm create vite@latest frontend -- --template react
cd frontend
npm install

Install dependencies:
npm install axios react-router-dom

---

## Environment Configuration

Create `.env` file in frontend root:

VITE_API_URL=http://localhost:8080/api

Usage in code:
const API_URL = import.meta.env.VITE_API_URL;

---

## API Service Layer Example

Create file: src/services/api.js

const API_URL = import.meta.env.VITE_API_URL;

export const loginUser = async (data) => {
const response = await fetch(`${API_URL}/auth/login`, {
method: "POST",
headers: {
"Content-Type": "application/json"
},
body: JSON.stringify(data)
});
return response.json();
};

---

## Protected Request Example

const token = localStorage.getItem("token");

fetch(`${API_URL}/users/1`, {
method: "GET",
headers: {
"Authorization": `Bearer ${token}`
}
});

---

## Route Protection (Frontend)

* Redirect user to login if token is missing
* Prevent access to protected pages

Example logic:
if (!token) {
navigate("/login");
}

---

## Error Handling

Handle common errors:

| Status Code | Meaning      | Action             |
| ----------- | ------------ | ------------------ |
| 401         | Unauthorized | Redirect to login  |
| 403         | Forbidden    | Show access denied |
| 500         | Server Error | Show error message |

---

## Token Expiration

* Token expires in 1 hour
* If expired:

  * Redirect user to login
  * Clear localStorage

---

## CORS Configuration

Backend allows requests from:

http://localhost:3000
http://localhost:5173

Make sure frontend runs on one of these ports.

---

## Recommended Frontend Structure

src/
├── pages/
│   ├── Login.jsx
│   ├── Dashboard.jsx
│   ├── Schemes.jsx
│   ├── Complaints.jsx
│
├── services/
│   └── api.js
│
├── components/
│   └── Navbar.jsx

---

## Important Notes

* Always send JWT token for protected APIs
* Do not expose sensitive data in frontend
* Validate inputs before sending requests
* Use proper error messages for better UX

---

## Conclusion

The backend is fully ready for integration.
Frontend developers can use this guide to build UI components and connect them with backend APIs efficiently.
