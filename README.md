# Getting Started

# WIn.Win.travel_TT

This project is a microservices-based application built with Spring Boot and Java 17. It utilizes Maven for dependency management and Docker for containerized deployment.

## 🏗 Architecture

The system architecture consists of two primary microservices and a relational database:

* **Auth API (`auth-service`)**: Built from the `auth_api` module, this service handles user authentication, JWT signing, and database operations. It connects directly to the PostgreSQL database and communicates with the Data API via HTTP requests (`SERVICE_B_URL`).
* **Data API (`data-service`)**: Built from the `data_api` module, this service is responsible for data transformation and processing. It runs independently and relies on an `INTERNAL_TOKEN` to securely communicate with the Auth API.
* **Database (`postgres_db`)**: A PostgreSQL container that stores the application's persistent data (such as users and process logs). It is automatically initialized using the `init.sql` script on startup.


## 🛠 Build Instructions
Before we start, configure project variables(use [.env.example](POSTGRES_DB=app_db
POSTGRES_USER=win
POSTGRES_PASSWORD=password
POSTGRES_PORT=5432

JWT_SECRET=very-long-random-string-for-signing-jwt-32-chars
JWT_EXPIRATION=86400000

INTERNAL_TOKEN=super-secret-key-123
SERVICE_B_URL=http://data-api:8081) as a template)
    

1. Compile the project and build the executable JAR files for both modules using Maven:
```
   mvn clean package -DskipTests
```
2. Build the Docker images for the microservices:
```
   docker-compose up -d --build
```
## 🌐 Available API Endpoints

### 🔐 Auth API (`auth-service`, port `8080`)
Handles user authentication and processes client requests.

**1. User Registration**
* **Method:** `POST`
* **URL:** `/api/auth/register`
* **Request Body (JSON):**
  ```json
  {
    "email": "user@example.com",
    "password": "yourpassword"
  }

**2. User Login**
* **Method:** `POST`
* **URL:** `/api/auth/register`
* **Request Body (JSON):**
  ```json
  {
    "email": "user@example.com",
    "password": "yourpassword"
  }
* **On success, you will get a JWT token:**
  ```json
  {
    "token": "..."
  }

**3. Text Processing (Requires Authentication)**
* **Method:** `POST`
* **URL:** `/api/process`
* **Headers:** Authorization: Bearer <your_jwt_token>
* **Request Body (JSON):**
  ```json
  {
  "text": "text to process"
  }
* **On success, you will get a reversed string:**
  ```json
  {
  "result": "ssecorp ot txet"
  }

**4. Text Transformation (Internal Endpoint)**
* **Method:** `POST`
* **URL:** `/api/transform`
* **Headers:** X-Internal-Token: <secret_internal_token> (default is super-secret-key-123)
* **Request Body (JSON):**
  ```json
  {
  "text": "Hello"
  }
* **On success, you will get a reversed string:**
  ```json
  {
  "text": "olleH"
  }
