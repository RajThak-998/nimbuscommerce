# NimbusCommerce

A modern, cloud-native e-commerce platform built with microservices architecture using Spring Boot and Spring Cloud.

## 🏗️ Architecture

![NimbusCommerce Architecture](architecture/nimbuscommerce.png)

NimbusCommerce follows a microservices architecture pattern with the following components:

- **API Gateway**: Central entry point for all client requests
- **Auth Service**: Authentication and authorization management
- **Customer Service**: Customer profile and management
- **Order Service**: Order processing and management
- **Logistics Service**: Delivery and logistics coordination
- **Notification Service**: Multi-channel notification handling

## 🚀 Tech Stack

### Core Technologies
- **Java**: 21
- **Spring Boot**: 3.5.7
- **Spring Cloud**: 2024.0.0
- **Build Tool**: Maven

### Key Dependencies
- **Spring Cloud Gateway**: API Gateway routing and filtering
- **Spring Security**: Authentication and authorization
- **JWT (JSON Web Tokens)**: Token-based authentication
- **Spring Data JPA**: Database persistence layer
- **PostgreSQL**: Primary database
- **Apache Kafka**: Event-driven messaging
- **gRPC & Protocol Buffers**: Inter-service communication
- **Lombok**: Boilerplate code reduction
- **Docker**: Containerization

## 📁 Project Structure

```
nimbuscommerce/
├── api-gateway/              # API Gateway service
├── auth-service/             # Authentication & Authorization service
├── customer-service/         # Customer management service
├── order-service/            # Order management service
├── logistics-service/        # Logistics & delivery service
├── notification-service/     # Notification handling service
├── architecture/             # Architecture diagrams
├── http-requests/            # API testing requests
│   ├── api-requests/        # REST API requests
│   └── grpc-requests/       # gRPC requests
└── docker-compose.yml        # Docker orchestration
```

## 🛠️ Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: 21 or higher
- **Maven**: 3.8 or higher
- **Docker**: Latest version
- **Docker Compose**: Latest version
- **PostgreSQL**: 15 or higher (if running locally)
- **Apache Kafka**: Latest version (if running locally)

## 🏃 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/nimbuscommerce.git
cd nimbuscommerce
```

### 2. Build All Services

Build all microservices using Maven:

```bash
# Build all services
mvn clean install

# Or build individual services
cd auth-service && mvn clean install
cd customer-service && mvn clean install
cd order-service && mvn clean install
cd logistics-service && mvn clean install
cd notification-service && mvn clean install
cd api-gateway && mvn clean install
```

### 3. Run with Docker Compose

The easiest way to run the entire platform:

```bash
docker-compose up -d
```

### 4. Run Services Individually

Each service can be run independently:

```bash
# Auth Service
cd auth-service
mvn spring-boot:run

# Customer Service
cd customer-service
mvn spring-boot:run

# Order Service
cd order-service
mvn spring-boot:run

# Logistics Service
cd logistics-service
mvn spring-boot:run

# Notification Service
cd notification-service
mvn spring-boot:run

# API Gateway (run this last)
cd api-gateway
mvn spring-boot:run
```

## 🔧 Configuration

### Database Configuration

Each service requires PostgreSQL database configuration. Update `application.properties` in each service:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Kafka Configuration

Configure Kafka connection in services that use event streaming:

```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=your-service-group
```

### JWT Configuration (Auth Service)

Configure JWT secret and expiration in `auth-service/application.properties`:

```properties
jwt.secret=your-secret-key
jwt.expiration=86400000
```

## 📡 API Testing

The project includes HTTP request files for testing APIs:

### REST API Requests
- **Auth Service**: `http-requests/api-requests/auth-service/`
  - Login: `login-request.http`
  - Validate Token: `validate-request.http`
  
- **Customer Service**: `http-requests/api-requests/customer-service/`
  - Create Customer: `create-customer.http`
  - Get Customer: `get-customer.http`
  - Update Customer: `update-patient.http`
  - Delete Customer: `delete-customer.http`

- **Order Service**: `http-requests/api-requests/order-service/`
  - Create Order: `create-order.http`
  - Get Order: `get-order.http`
  - Update Order: `update-order.http`
  - Delete Order: `delete-order.http`

### gRPC Requests
- Customer Service: `http-requests/grpc-requests/CustomerService/`
- Logistics Service: `http-requests/grpc-requests/OrderLogisticsService/`

## 🧪 Testing

Run tests for all services:

```bash
# Run all tests
mvn test

# Run tests for specific service
cd auth-service && mvn test
```

## 🐳 Docker Support

Each service includes a Dockerfile for containerization. Build and run individual services:

```bash
# Build Docker image
docker build -t nimbuscommerce/auth-service:latest ./auth-service

# Run container
docker run -p 8080:8080 nimbuscommerce/auth-service:latest
```

## 🔐 Security

- JWT-based authentication and authorization
- Spring Security integration
- Token validation at API Gateway level
- Role-based access control (RBAC)

## 📊 Service Communication

The platform uses two communication patterns:

1. **Synchronous Communication**: 
   - REST APIs for external clients
   - gRPC for inter-service communication

2. **Asynchronous Communication**:
   - Apache Kafka for event-driven messaging
   - Event streaming for order updates, notifications, etc.

## 🌐 API Gateway Routes

All client requests go through the API Gateway, which routes to appropriate services:

- `/auth/**` → Auth Service
- `/api/customers/**` → Customer Service
- `/api/orders/**` → Order Service
- `/api/logistics/**` → Logistics Service
- `/api/notifications/**` → Notification Service

## 📈 Monitoring & Observability

(To be implemented)
- Spring Boot Actuator endpoints
- Distributed tracing
- Centralized logging
- Health checks

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

**Your Name**
- GitHub: [@Rajdev Singh](https://github.com/RajThak-998)

## 🙏 Acknowledgments

- Spring Boot and Spring Cloud communities
- Apache Kafka
- gRPC and Protocol Buffers
- PostgreSQL

---

**Note**: This is an active development project. Features and documentation are continuously being updated.

