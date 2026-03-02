# Room Reservation System - Microservices Architecture

**Final Project – Introduction to Software Architecture**

## 👥 Team Members

- Gabriel Mesquita Oliveira
---

## 📋 1. Problem Description

Companies and institutions face challenges managing shared spaces (meeting rooms, laboratories, auditoriums). Common problems include:

- **Scheduling conflicts**: Multiple reservations for the same time slot
- **Lack of control**: No record of who reserved and when
- **Inefficiency**: Manual processes prone to errors
- **Resource waste**: Idle rooms due to lack of visibility

### Real-World Context
This system solves a problem found in corporate and academic environments where there's a need to:
- Manage reservations for multiple resources
- Ensure no time slot overlaps
- Maintain records of users and their reservations
- Apply specific business rules (business hours, minimum/maximum duration)

---

## 🎯 2. System Objectives

Develop a **reservation management system** that:

### Main Features
1. **User Management**
   - User registration and profile representation

2. **Reservation Management**
   - Create reservations with business rule validation
   - Validate associated user and time slots

3. **Implemented Business Rules**
   - ✅ User must exist before creating a reservation
   - ✅ Communication between microservices to ensure data consistency

---

## 🏛️ 3. Architectural Style Adopted

### **Microservices Architecture**

The system was designed using the **microservices** pattern, where the application is decomposed into independent services, each responsible for a specific business domain.

#### Implemented Microservices

```text
┌─────────────────────────────────────────────────────────────┐
│                    NGINX REVERSE PROXY                       │
│                         (Port 80)                            │
└──────────────────────────┬──────────────────────────────────┘
                           │
           ┌───────────────┼───────────────┐
           │                               │
           ▼                               ▼
    ┌──────────┐                   ┌─────────────┐
    │   USER   │                   │ RESERVATION │
    │ SERVICE  │◄─────────────────►│   SERVICE   │
    │  (8081)  │    REST Client    │   (8082)    │
    └──────────┘                   └─────────────┘
         │                               │
         │                               │
         ▼                               ▼
    [PostgreSQL]                    [PostgreSQL]
    (user_db)                       (reservation_db)
```

#### Architecture Components

**Note**: Services are built using **Quarkus 3.32.1** for optimal performance, fast startup time, and low memory footprint.

1. **API Gateway / Proxy (Nginx - Port 80)**
   - Single entry point routing requests to specific services
   - Replaces the need for a separate Gateway service
   - High availability and static serving capabilities

2. **User Service**
   - User management logic
   - Independent PostgreSQL database (`user_db`)
   - Uses Quarkus 3.32.1

3. **Reservation Service**
   - Reservation management logic
   - Direct communication with User Service (Quarkus REST Client)
   - Independent PostgreSQL database (`reservation_db`)
   - Uses Quarkus 3.32.1

---

## 📐 4. Architecture Diagram

### Macro View

```text
                    PRESENTATION LAYER
                            │
                            ▼
                    ┌───────────────┐
                    │     Nginx     │ ◄── Single Entry Point
                    └───────┬───────┘
                            │
            ┌───────────────┼───────────────┐
            │                               │
            ▼                               ▼
    ┌───────────────┐              ┌────────────────┐
    │ User Service  │              │ Reservation    │
    │               │◄─────────────┤ Service        │
    │ • CRUD Users  │ REST Client  │ • Business     │
    │               │              │   Rules        │
    └───────┬───────┘              └────────┬───────┘
            │                               │
            │                               │
            ▼                               ▼
      ┌──────────┐                   ┌──────────┐
      │PostgreSQL│                   │PostgreSQL│
      │ User DB  │                   │ Res. DB  │
      └──────────┘                   └──────────┘
```

### Internal Service Architecture (Clean Architecture concepts)

Both services utilize structured design concepts common to Clean Architecture and DDD, ensuring correct dependency flows between business logic and infrastructure elements (like Panache Repositories and REST resources).

---

## 💡 5. Justification of Architectural Decisions

### 5.1 Why Microservices?

**Decision**: Adopt microservices architecture instead of monolith

**Justification**:
1. **Domain Separation**: User and Reservation are distinct bounded contexts (DDD)
2. **Independent Scalability**: Reservation service may have a different load profile than the user service
3. **Resilience**: Failure in one service doesn't bring down the entire system

### 5.2 Nginx API Gateway

**Decision**: Implement Nginx as reverse proxy and entry point

**Justification**:
1. **Client Simplification**: Single URL to access all services (port 80)
2. **Performance**: Nginx is highly optimized for routing and static file handling

### 5.3 Framework Choice: Quarkus

**Decision**: Use Quarkus (replacing Spring Boot)

**Justification**:
1. **Cloud Native**: Designed for containers and Kubernetes with low memory footprint
2. **Performance**: Extremely fast startup times and optimal resource utilization
3. **Developer Experience**: Live reloading capabilities out of the box

### 5.4 PostgreSQL Database

**Decision**: Use PostgreSQL instead of H2 for persistent storage

**Justification**:
1. **Reliability**: Mature, production-ready relational database
2. **Data Consistency**: Retains states across application restarts
3. **Multi-DB Support**: Utilizing a single PostgreSQL instance for multiple databases (`user_db` and `reservation_db`)

### 5.5 Synchronous Communication with REST Client

**Decision**: Use Quarkus REST Client for User ↔ Reservation communication

**Justification**:
1. **Simplicity**: Declarative abstraction over HTTP calls makes cross-service requests straightforward
2. **Type Safety**: Avoids manual JSON parsing and handles DTO integration smoothly

---

## 🧪 6. Tests and Business Rule Validation

The system contains **7 automated tests** covering essential rules and integrations:

### User Service - 2 tests
- Validates typical Use Case workflows and User persistence.

### Reservation Service - 5 tests
- Validates the reservation logic
- Verifies integration via REST Assured and Quarkus test mock components.

### Run Tests

Testing utilizes JUnit 5, REST Assured, and AssertJ:

```bash
# User Service
cd user-service
./mvnw verify

# Reservation Service
cd reservation-service
./mvnw verify
```

---

## 🚀 7. Execution Instructions

### 7.1 Prerequisites
- **Docker** and **Docker Compose**
- **Java 25** (for source compilation if needed outside Docker)
- **Maven** (optional, uses wrapper `./mvnw`)

### 7.2 Run the System

The recommended way to start the system using Docker Compose:

```bash
cd infra
docker-compose up -d --build
```

The system will automatically:
1. Initialize the PostgreSQL container with `user_db` and `reservation_db`
2. Build and start the `user-service` and `reservation-service`
3. Launch the `nginx` proxy serving at port `80`

**To stop**:
```bash
cd infra
docker-compose down
```

### 7.3 Access the System

| Service | Address |
|---------|---------|
| **Nginx Entry Point** | http://localhost:80 |
| **PostgreSQL Database** | `localhost:5432` |
| **User Service Direct Access** | `http://localhost:8081` (Internal) |
| **Reservation Service Direct Access** | `http://localhost:8082` (Internal) |

*Swagger UI endpoints will be natively exposed at the backend paths `/q/swagger-ui` depending on proxy setups.*

---

## 🛠️ 8. Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 25 | Main language |
| **Quarkus** | 3.32.1 | Base framework |
| **PostgreSQL** | 15 | Relational database |
| **Nginx** | alpine | Reverse proxy / API Gateway |
| **Docker Engine** | | Containerization & Compose |
| **MapStruct** | 1.5.x/1.6.x | Object mapping (Entities ↔ DTOs) |
| **Record Builder** | 43 | Boilerplate reduction for Java Records |
| **Hibernate ORM / Panache**| | Data persistence |
| **JUnit 5 / REST Assured** | latest | Testing |

---

## 📦 9. Project Structure

```text
caixaverso/
│
├── README.md                           # This file
├── infra/                              # Infrastructure config
│   ├── docker-compose.yml              # All services orchestrated via Docker
│   ├── nginx.conf                      # Nginx proxy config
│   └── postgres-init.sh                # DB initialization scripts
│
├── user-service/                       # User Microservice
│   ├── src/
│   │   ├── main/java/                  # Domain & Resources
│   │   └── test/java/                  # 2 tests
│   └── pom.xml                         # Java 25 & Quarkus 3.32
│
└── reservation-service/                # Reservation Microservice
    ├── src/
    │   ├── main/java/                  # Domain, Sync Clients & Resources
    │   └── test/java/                  # 5 tests
    └── pom.xml                         # Java 25 & Quarkus 3.32
```

---

## 🎓 10. Architecture Concepts Demonstrated

This project exemplifies the following concepts:

### 10.1 Architectural Patterns
- ✅ **Microservices**: Logical application decomposition
- ✅ **Clean Architecture / DDD**: Clear separation of endpoints, DTOs, and mappings
- ✅ **API Gateway**: Single entry point implementation via Nginx proxy

### 10.2 Design Patterns
- ✅ **Repository Pattern**: Simplified with Hibernate with Panache
- ✅ **DTO Pattern**: Separation between persistence entities and response models
- ✅ **Mapper Pattern**: MapStruct providing high-speed transformations

### 10.3 Architectural Qualities
- ✅ **Low Coupling**: Completely separate application scopes
- ✅ **Testability**: Fast tests mapped with mocked Quarkus services
- ✅ **Portability**: The entire architecture is containerized out-of-the-box

---

## 📊 11. Technical Decisions Summary

| Aspect | Decision | Justification |
|---------|---------|---------------|
| **Architecture** | Microservices | High domain isolation and clear bounds |
| **Framework** | Quarkus | Optimized for modern developer experience & containers |
| **Proxy** | Nginx | Extremely reliable routing with minimal overhead |
| **Data Storage** | PostgreSQL | Proven ACID-compliant structured document store |
| **Mapping** | MapStruct | Safe, compile-time object property generation |

---

## 🎯 12. Results and Conclusion

### Achieved Objectives
✅ Fully functional system built natively on modern cloud capabilities
✅ Microservices separated correctly and sharing no mutable state
✅ Replaced heavyweight internal gateways with lightweight infrastructure solutions
✅ Highly maintainable ecosystem containerized by Docker

### Possible Evolutions
- Add a Service Mesh (e.g. Istio) for internal communications
- Adopt Quarkus native binary compilation for sub-second start times
- Integrate Keycloak for JWT-based Authentication
- Export distributed traces metrics using OpenTelemetry

---

## 📄 License

Educational project developed for the Introduction to Software Architecture course.

---

**Submission Date**: [Insert date]  
**Course**: [Insert course]  
**Institution**: [Insert institution]
