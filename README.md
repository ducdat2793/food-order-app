# Food Order App - Microservices Demo

This is a Java Spring Boot microservices-based project that demonstrates core concepts of a modern distributed backend system. It is built to showcase technologies like gRPC, Kafka, Eureka, CI/CD pipelines, and API Gateway routing.

---

## ✨ Technologies Used

* **Java 17 & Spring Boot**
* **Spring Security + JWT**
* **gRPC** for internal service communication
* **Kafka + Kafka UI** (via Docker Compose)
* **Eureka Server** for service discovery
* **API Gateway** for routing requests
* **Spring Data JPA** with **H2 Database** (in-memory)
* **Swagger** documentation available at the API Gateway (port `8080`)
* **CI/CD** via GitHub Actions
* **Docker (Quickstart Terminal)** for Kafka

---

## 📁 Project Structure

* `eureka-server`: Service registry
* `api-gateway`: Gateway that routes requests to downstream services
* `user-service`: Handles user management and authentication
* `menu-service`: Manages food menu items
* `order-service`: Accepts and processes food orders
* `notification-service`: Sends order-related notifications

---

## ✅ Prerequisites

* Java 17
* Maven
* Git
* Docker Toolbox or Docker Quickstart Terminal (for Kafka)

---

## 🚀 How to Run the Project Locally

### 1. Clone the repository:

```bash
git clone https://github.com/your-username/food-order-app.git
cd food-order-app
```

### 2. Build the services

Build (menu, order) services to generate `target/` folders (required for gRPC-generated stubs):

```bash
mvn clean install -DskipTests=false
```

You can also use the CI pipeline on GitHub which automatically builds and uploads artifacts.

### 3. Start Kafka using Docker Compose (in Quickstart Terminal):

```bash
docker compose up -d
```

Ensure Kafka and Zookeeper are up.

### 4. Run the services

Run each microservice from your IDE or terminal:

```bash
cd eureka-server && mvn spring-boot:run
cd api-gateway && mvn spring-boot:run
cd user-service && mvn spring-boot:run
cd menu-service && mvn spring-boot:run
cd order-service && mvn spring-boot:run
cd notification-service && mvn spring-boot:run
```

---

## 📃 Swagger API Documentation

Visit the following URL to explore APIs:

```
http://localhost:8080/webjars/swagger-ui/index.html
```

> Swagger is configured through the **API Gateway**, so all downstream service endpoints are available under one gateway.

### 🔐 How to Use JWT with Swagger

1. **Register a user** using the `POST /user/register` endpoint.
2. **Login** with that user via `POST /user/login`.
3. The response will include a **JWT token**.
4. Click the **"Authorize"** button on Swagger UI (top right).
5. Paste the token in the format: `Bearer <your-token>`.
6. You can now call protected endpoints across microservices (order, menu, etc).

---

## ✍️ CI/CD Overview

* CI is handled via GitHub Actions (`.github/workflows/ci.yml`)
* On each push to the `master` branch, the pipeline:

  * Builds each microservice
  * Runs tests
  * Uploads JAR files as GitHub Artifacts

### Sample Artifact:

* After a push, go to the `Actions` tab in GitHub and select the latest run to download service `.jar` files.

---

## ⚠️ Important Notes

* **Build before running**: gRPC relies on generated classes in `target/` folder; services will fail to start if not built.
* **gRPC Port Mapping**: gRPC servers typically run on custom ports (e.g. 9090). These are registered with Eureka manually via metadata.
* **Kafka UI**: You can integrate tools like Kafka UI to visualize topics/messages for easier debugging.

---

## 🚀 Deployment

This project demonstrates CI/CD at a basic level. For full deployment (CD), you could:

* Extend GitHub Actions to deploy to a remote VM or container registry
* Use Docker Compose for service orchestration (future improvement)

---

## 🚀 Final Thoughts

This project is built to show employers and collaborators:

* Understanding of distributed architecture (microservices)
* Secure communication with JWT
* Inter-service communication using gRPC
* Asynchronous messaging with Kafka
* Dynamic service discovery using Eureka
* Gateway-based API exposure
* CI/CD with GitHub Actions

> Contributions and suggestions welcome!
