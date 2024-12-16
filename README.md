# Spring Boot Pre-BootCamp Application

This project is a **Spring Boot application** designed to handle authentication, role-based access control, and CRUD operations for students and subjects. It includes JWT-based authentication, role distinction (Student vs Admin), and uses JPA for database management.

## **Features**

1. **Authentication**:

   - Register users (students and admins).
   - Login to generate a JWT token.
   - Secure API endpoints using JWT.

2. **Role-based Access Control**:

   - **Student**: Can only view their own data.
   - **Admin**: Can view and manage all students and subjects.

3. **CRUD Operations**:

   - Add and retrieve students.
   - Add and retrieve subjects linked to students.

4. **Technology Stack**:
   - Spring Boot (REST API)
   - Spring Security (JWT Authentication)
   - JPA with Hibernate (Database)
   - H2 Database (in-memory database for testing)
   - Postman (for testing APIs)

---

## **Prerequisites**

1. JDK Version : Java 19 or higher..
2. Build Tool: Maven 3.8+.
3. An IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code).
4. Database: H2 Database
5. Postman (for testing API calls).

---

## **Project Structure**

```bash
Pre_BootCamp
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── Pre_BootCamp
│   │   │           ├── controller       # API endpoint handlers
│   │   │           │   ├── AuthController.java
│   │   │           │   ├── StudentController.java
│   │   │           │   ├── SubjectController.java
│   │   │           ├── model            # Entity classes for JPA
│   │   │           │   ├── Auth.java
│   │   │           │   ├── Student.java
│   │   │           │   ├── Subject.java
│   │   │           ├── repository       # Repositories for database access
│   │   │           │   ├── AuthRepository.java
│   │   │           │   ├── StudentRepository.java
│   │   │           │   ├── SubjectRepository.java
│   │   │           ├── service          # Business logic and utilities
│   │   │           │   ├── AuthService.java
│   │   │           │   ├── StudentService.java
│   │   │           │   ├── SubjectService.java
│   │   │           │   ├── JwtService.java
│   │   │           ├── config           # Security and application configurations
│   │   │           │   ├── SecurityConfig.java
│   │   │           │   ├── JwtFilter.java
│   │   ├── resources
│   │   │   ├── application.properties
├── pom.xml                              # Maven configuration file
└── README.md                            # Documentation for the project

```

### What is JWT?

JSON Web Token (JWT) is a compact and self-contained way for securely transmitting information between parties as a JSON object. The information is digitally signed, so it can be verified and trusted.

JWT is commonly used in authentication systems because it allows the server to authenticate a user without maintaining session information on the server-side. Instead, the user's authentication details are embedded within the token, which is then sent to the server for verification.

### JWT Structure

A JWT is composed of three parts:

1. **Header**: Contains metadata about the token, such as the type (`JWT`) and the signing algorithm (e.g., HMAC SHA256 or RSA).
2. **Payload**: Contains the claims, which are statements about an entity (typically, the user) and additional data.
3. **Signature**: Used to verify that the sender of the JWT is who it says it is and to ensure that the message wasn't altered along the way.

A typical JWT looks like this:
Where:

- **Header**: `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9`
- **Payload**: `eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ`
- **Signature**: `SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c`

### JWT Authentication Flow

1. **User Login**:

   - The user sends their username and password to the backend in a login request.
   - The backend verifies the credentials against the database.
   - If the credentials are valid, the backend generates a JWT token and sends it back to the client.

2. **Accessing Protected Resources**:

   - For subsequent requests, the user includes the JWT token in the `Authorization` header as `Bearer <token>`.
   - The backend verifies the token, checking its validity (signature and expiration).
   - If the token is valid, the backend processes the request; otherwise, it returns an error (usually 401 Unauthorized).

3. **Token Expiration**:
   - JWT tokens are often set to expire after a certain period (e.g., 1 hour). After expiration, the user needs to log in again to get a new token.

### Backend Setup

### How to Run the Application

1. **Clone the repository**

```bash
git clone <repository-url>
cd Pre_BootCamp
```

2. **Configure the Database:**

   Update src/main/resources/application.properties as needed::

```bash
 # H2 Database configuration
  spring.datasource.url=jdbc:h2:mem:prebootcampdb
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.username=<Your-username>
  spring.datasource.password=<Your-password>
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

3. **Build and Run the Backend**

   Use Maven to build the project:

```bash
 mvn spring-boot:run
```

4. **Start the application using:**

```bash
 mvn spring-boot:run
```

The application will be available at http://localhost:9090.

## API Documentation

## Student APIs

## 1. **POST** `/register`

## Register a User

- Endpoint: POST /register
- Description: Registers a user (default role: STUDENT).

### Request Body

---

```bash
curl --location 'http://localhost:9090/register' \
--header 'Content-Type: application/json' \
n--data '{
 "username": "johndoe",
 "password": "password123"
}
```

- 201 Created: User successfully registered.
- 400 Bad Request: Missing fields or invalid input.

### Response Body

---

```json
{
  "message": "Register successfully"
}
```

### 2. **POST** `/login`

---

- Endpoint: POST /authentication
- Description: Authenticates a user and returns a JWT token.

### Request Body

---

```bash
curl --location 'http://localhost:9090/authentication' \
--header 'Content-Type: application/json' \
--data '{
    "username": "johndoe",
  "password": "password123"
}'
```

- 200 OK: Returns a JWT token if login is successful.
- 401 Unauthorized: Invalid credentials.

### Response Body

---

```bash
{
  "token": "<jwt-token>"
}
```

### 3. **POST** `/addStudent`

---

- Endpoint: POST /addStudent
- Description: Adds a student (Admin only).

### Request Headers

---

```makefile
Authorization: Bearer <JWT_TOKEN>
```

### Request Body

---

```bash
curl --location 'http://localhost:9090/addStudent' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer ...' \
--data '{
	"name":"John Doe",
    "address":"123 Main St"
}
```

### Response Body

---

```json
{
  "message": "Student added successfully"
}
```

- 201 Created: Donation successfully added.
- 403 Forbidden: Invalid or expired JWT token.

## Response Body

### 4. **GET** `/getStudent`

---

- Endpoint: GET /getStudent
- Description: Returns student data.

- Authorization:
  - STUDENT: Returns only the authenticated student's data.
  - ADMIN: Returns all student data.

#### Headers

```makefile
Authorization: Bearer <JWT_TOKEN>
```

### Response (STUDENT):

---

```bash
curl --location 'http://localhost:9090/getStudent' \
--header 'Authorization: Bearer <>'
```

- 200 OK: Returns an array of donations made by the user.
- 403 Forbidden: Invalid or expired JWT token.

### Response (STUDENT):

---

```bash
[
  {
    "id": 1,
    "name": "John Doe",
    "address": "123 Main St"
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "address": "456 Elm St"
  }
]
```

## Subject APIs

### 5. **POST** `addSubject`

- Endpoint: POST /addSubject
- Description: Adds a subject to a student (Admin only).

### Request Headers

---

```makefile
Authorization: Bearer <JWT_TOKEN>
```

### Request Body

---

```json
curl --location 'http://localhost:9090/addSubject' \
--data '{
    "name": "english",
    "student": {
        "id": 1
    }
}'
```

- 201 Created: Donation successfully added.
- 403 Forbidden: Invalid or expired JWT token.

### Response

---

```bash
{
  "messsage": "Subject added successfully"
}
```

### 6. **GET** `getSubject`

- Endpoint: GET /getSubject
- Description: Returns all subjects..

### Request Headers

---

```makefile
{
  "Authorization": "Bearer <jwt-token>"
}
```

### Request Body

```bash
curl --location 'http://localhost:9090/getSubject' \
--header 'Authorization: Bearer <>'
```

### Response

---

- 201 Created: Donation successfully added.
- 403 Forbidden: Invalid or expired JWT token.

```bash
[
  {
    "id": 1,
    "name": "english",
    "student": {
      "id": 1,
      "name": "John Doe",
      "address": "123 Main St"
    }
  }
]
```

## Developed By

- Asjad Samdani
