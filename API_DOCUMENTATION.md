API DOCUMENTATION – SMART VILLAGE MANAGEMENT SYSTEM
1. Introduction

The Smart Village Management System provides a RESTful API-based backend developed using Spring Boot. The APIs support various functionalities such as user management, announcements, complaints, government schemes, and scheme applications.

All APIs are tested using Postman and follow a structured request-response model using JSON.

2. Base URL
http://localhost:8080/api
3. Authentication Mechanism

The system uses JWT (JSON Web Token) for authentication and authorization.

Steps for Authentication:
Register a user using /auth/register
Login using /auth/login
Receive JWT token in response
Include token in header for secured APIs:
Authorization: Bearer <JWT_TOKEN>
4. User Roles

The system supports role-based access:

ADMIN
SARPANCH
CITIZEN

Each role has different permissions for accessing APIs.

5. API MODULES
5.1 Authentication APIs
Method	Endpoint	Description
POST	/auth/register	Register a new user
POST	/auth/login	Authenticate user and generate JWT token
5.2 Announcement APIs
Method	Endpoint	Description
POST	/announcements/post	Create announcement
PUT	/announcements/{id}	Update announcement
DELETE	/announcements/{id}	Delete announcement
GET	/announcements/{id}	Get announcement by ID
GET	/announcements	Get all announcements
GET	/announcements/type?type=NOTICE	Filter by type
GET	/announcements/status?status=ACTIVE	Filter by status
GET	/announcements/active	Get active announcements
GET	/announcements/search?keyword=...	Search announcements
GET	/announcements/recent?since=...	Get recent announcements
PATCH	/announcements/{id}/status	Update status
GET	/announcements/posted-by/{userId}	Get by creator
5.3 Complaint APIs
Method	Endpoint	Description
POST	/complaints	Raise complaint
PUT	/complaints/{id}	Update complaint
DELETE	/complaints/{id}	Delete complaint
PUT	/complaints/{id}/assign/{handlerId}	Assign handler
PUT	/complaints/{id}/status	Update complaint status
GET	/complaints/{id}	Get complaint by ID
GET	/complaints	Get all complaints
GET	/complaints/my	Get user complaints
GET	/complaints/status	Filter by status
GET	/complaints/handler/{id}	Get by handler
GET	/complaints/recent	Get recent complaints
GET	/complaints/search	Search complaints
5.4 Scheme APIs
Method	Endpoint	Description
POST	/schemes	Create scheme
PUT	/schemes/{id}	Update scheme
PUT	/schemes/{id}/deactivate	Deactivate scheme
GET	/schemes/{id}	Get scheme by ID
GET	/schemes/active	Get active schemes
GET	/schemes	Get all schemes
GET	/schemes/posted-by/{id}	Get schemes by user
GET	/schemes/search	Search schemes
POST	/schemes/eligibility	Check eligibility
5.5 Scheme Application APIs
Method	Endpoint	Description
POST	/applications	Apply for scheme
PUT	/applications/{id}	Update application
PUT	/applications/{id}/status/{status}	Update application status
DELETE	/applications/{id}	Delete application
GET	/applications	Get all applications
GET	/applications/{id}	Get application by ID
GET	/applications/my	Get user applications
GET	/applications/scheme/{schemeId}	Get by scheme
GET	/applications/status/{status}	Filter by status
PUT	/applications/{id}/cancel	Cancel application
5.6 User Management APIs
Method	Endpoint	Description
PUT	/users/assign-role/{id}	Assign role
PUT	/users/admin/update/{id}	Admin update user
GET	/users	Get all users
PUT	/users/update-profile	Update profile
GET	/users/{id}	Get user by ID
GET	/users/admin/{id}	Admin fetch user
GET	/users/by-role	Filter by role
PUT	/users/block/{id}	Block user
PUT	/users/activate/{id}	Activate user
GET	/users/active	Get active users
6. Request & Response Format
All requests and responses are in JSON format
Proper HTTP status codes are used:
200 OK
201 Created
400 Bad Request
401 Unauthorized
404 Not Found
7. Security Features
JWT-based authentication
Role-based authorization
Protected endpoints using Spring Security
Token expiration handling
8. Testing
All APIs are tested using Postman
A complete Postman collection is included
Supports easy API validation and debugging
9. Conclusion

The backend system is fully functional with secure and scalable REST APIs. It supports all major village management operations including governance, complaints, announcements, and welfare schemes.