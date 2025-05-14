# 🚀 Hospital Management API

Welcome to the **Hospital Management API**! This API provides a comprehensive solution for managing hospitals, including doctors, patients, appointments, and more.

## 📌 Features

* 📋 Manage doctors, patients, appointments, and medical records.
* 🔐 Secure API with JWT authentication.
* 🌐 Comprehensive API documentation with Swagger UI.
* ✅ Scalable and maintainable architecture with Spring Boot.

## 🚀 Getting Started

### 📌 Prerequisites

* Java 17+
* Maven 3+
* MySQL 8+
* Git

### 📌 Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/MarioHMis/hospital-management.git
   cd hospital-management
   ```

2. **Configure Environment Variables:**

   * Copy the `.env.example` file and rename it to `.env`.
   * Edit the `.env` file and set your database credentials.

   ```bash
   cp .env.example .env
   ```

### 📌 Database Configuration

* Make sure you have MySQL running.
* Create a database named `hospital_management`.
* The database credentials are managed through the `.env` file.

### 📌 Running the Application

1. **Build the project:**

   ```bash
   mvn clean install
   ```

2. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

### 📌 Accessing the API

* API Documentation: `http://localhost:8080/swagger-ui.html`

### 📌 Authentication

* The API uses JWT for authentication.
* Register and login to receive a JWT token.
* Use the token in the Authorization header for secured endpoints.

### 📌 Environment Variables (.env)

* Ensure your `.env` file is configured with the following variables:

```plaintext
DB_URL=jdbc:mysql://localhost:3306/hospital_management
DB_USERNAME=your_database_username
DB_PASSWORD=your_database_password
SERVER_PORT=8080
```

### 📌 Contributing

* Fork the repository.
* Create a new branch (`feature/your-feature`).
* Make your changes and commit them.
* Push to your forked repository.
* Create a Pull Request.

### 📌 License

This project is licensed under the MIT License.
