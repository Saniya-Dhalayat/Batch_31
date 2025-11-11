# Salary API
<img width="1443" height="428" alt="image" src="https://github.com/user-attachments/assets/59718fb0-a48d-4e67-b41a-96313702a88b" />

| **Author** | **Created on** | **Version** | **Last updated by** | **Last Edited On** | **Level** | **Reviewer** |
|-------------|----------------|--------------|---------------------|--------------------|------------|---------------|
| Saniya | 2025-11-12 | 1.0 | Saniya | 2025-11-12 | Internal Review | Team |


---

##  Table of Contents
- [Introduction](#introduction)
- [Purpose](#purpose)
- [Prerequisites](#prerequisites)
- [Local Setup Steps](#local-setup-steps)
- [Environment Deployment (Dev/UAT/Prod)](#environment-deployment-(dev/uat/prod))
- [Monitoring & Observability](#monitoring-&-observability)
- [Troubleshooting](#troubleshooting)
- [Contact Information](#contact-information)
- [References](#references)

## Introduction

The **Salary API** is part of the OT-MICROSERVICES ecosystem and is responsible for managing employee salary data, including creation, retrieval, and query operations.  
It is a lightweight, scalable, and stateless Spring Boot microservice

---

##  Purpose

The goal of this API is to:
- Provide a centralized service for handling salary-related data and operations.  
- Ensure consistency and traceability of salary records across environments.  

---

##  Prerequisites

Ensure the following are installed before setup:

- **Java:** JDK 11 or higher  
- **Build Tool:** Maven or use bundled `./mvnw`  
- **Database:** ScyllaDB (Cassandra-compatible)  
- **Cache:** Redis  
- **Migration Tool:** `migrate` CLI  
- **Others:** Git, Docker (optional for local runs)

---

## Local Setup Steps

### **Step 1** Clone Repository
```bash
git clone https://github.com/OT-MICROSERVICES/salary-api.git
cd salary-api
```
---

### **Step 2** Start Dependencies
```bash
docker run -d -p 9042:9042 scylladb/scylla
docker run -d -p 6379:6379 redis
```
---

### **Step 3** Build & Run
```bash
make build
make run-migrations
java -jar target/salary-0.1.0-RELEASE.jar
```
---

### **Step 4** Validate

- Swagger: http://localhost:8080/salary-documentation

- Health: http://localhost:8080/actuator/health

## Environment Deployment (Dev/UAT/Prod)

- Provision ScyllaDB & Redis.

- Configure environment variables (application-<env>.yml).

- Run migrations:
```bash
make run-migrations
```
- Deploy using Docker/Kubernetes

- Verify via health and API endpoints.

## Monitoring & Observability

- Health Check: /actuator/health

- Metrics: /actuator/prometheus

- Logs: Standard Spring Boot logs (centralized via ELK/Loki)

---

## Troubleshooting

| **Issue**          | **Check**                     |
|---------------------|------------------------------|
| App not starting    | DB/Redis connectivity         |
| Migration failed    | `migration.json` configuration |
| Health failing      | Port or configuration mismatch |

---

## Contact Information
| **Name** | **Email** |
|-----------|-----------|
| Saniya | saniya.dhalayat.snaatak@mygurukulam.co |

---

## References

| **Resource** | **Link** |
|---------------|----------|
| Salary API Repository | [https://github.com/OT-MICROSERVICES/salary-api](https://github.com/OT-MICROSERVICES/salary-api) |
| ScyllaDB Documentation | [https://docs.scylladb.com/](https://docs.scylladb.com/) |
| Redis Documentation | [https://redis.io/docs/](https://redis.io/docs/) |

