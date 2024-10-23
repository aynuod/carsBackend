
---

# Cars Backend (Spring Boot)

This is the backend part of the **Voiture Shop App**, built with **Spring Boot** and **Java 17**, and Dockerized for easy deployment. The project includes integration with **Spring Boot Actuator**, **Prometheus**, and **Grafana** for monitoring and performance visualization. This guide provides steps for setting up, building, running, and monitoring the backend service using Docker Compose.

## Prerequisites

Make sure you have the following installed:

- **JDK 17**: [Download JDK 17 here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
- **Docker**: [Get Docker here](https://www.docker.com/get-started).
- **Maven**: [Installation guide](https://maven.apache.org/install.html).
- **Git**: A GitHub account is recommended for cloning and managing the project.

## Setup and Build Instructions

### ⚠️ Important Warnings

1. **Maven Build Stage**: The Dockerfile does not contain a build stage, so you must manually build the project using Maven before creating the Docker image. Skipping this step will result in errors during image creation.
2. **Ports in Use**: Ensure that the ports `3000`, `8080`, `9091`, and `3001` are not in use by other services on your machine, or adjust them in the `docker-compose.yml` file accordingly.

### 1. Clone the Repository

First, clone the repository from GitHub and navigate into the project directory:

```bash
git clone https://github.com/aynuod/carsBackend.git
cd carsBackend
```

### 2. Build the Backend with Maven

Before building the Docker image, ensure the project is compiled by running the following Maven command:

```bash
mvn clean package -DskipTests
```

> ⚠️ **Note**: The tests are skipped here using the `-DskipTests` flag to speed up the build process. You can remove this flag if you want to run the tests.

### 3. Build the Docker Image

Once the project is built, create the Docker image for the backend service using:

```bash
docker build -t backend-spring .
```

This will generate a Docker image for the backend using the `Dockerfile`.

### 4. Run Docker Compose

Now you can run all the services (backend, frontend, PostgreSQL, Prometheus, Grafana) defined in the Docker Compose file:

```bash
docker-compose up -d
```

This command will start the services in detached mode, including the backend with PostgreSQL, the React frontend, Prometheus for scraping metrics, and Grafana for monitoring.

### 5. Verify Services

Once Docker Compose is up, verify that all services are running:

```bash
docker-compose ps
```

You should see `backend`, `postgres`, `frontend`, `prometheus`, and `grafana` running.

## Monitoring with Spring Boot Actuator, Prometheus, and Grafana

### 1. Spring Boot Actuator

Spring Boot Actuator is used to expose operational information about the running application. Metrics such as system health, memory usage, and more are exposed to Prometheus. It is enabled in the backend, and Prometheus scrapes these metrics to make them available for visualization in Grafana.

### 2. Accessing Prometheus

Prometheus is used to scrape metrics from the backend and monitor them. It will be available at:

- **Prometheus URL**: [http://localhost:9091](http://localhost:9091)

You can query various metrics such as:

- `http_server_requests_seconds_count`: HTTP request counts.
- `jvm_memory_used_bytes`: Memory usage.
- `process_cpu_usage`: CPU usage.

### 3. Accessing Grafana

Grafana is available for visualizing the metrics scraped by Prometheus. To access Grafana, open:

- **Grafana URL**: [http://localhost:3001](http://localhost:3001)

### 4. Setting Up Dashboards in Grafana

#### Login to Grafana

The default login credentials for Grafana are:

- **Username**: `admin`
- **Password**: `admin` (you will be prompted to change it on first login).

#### Add Prometheus as a Data Source

1. Navigate to **Configuration** → **Data Sources**.
2. Click **Add data source**, select **Prometheus**, and set the URL to `http://prometheus:9090`.

#### Import a Dashboard

1. Navigate to **Create** → **Import**.
2. You can use a pre-built dashboard from Grafana or create a custom one for Spring Boot metrics.

Common metrics include:

- `jvm_memory_used_bytes`: Memory usage.
- `http_server_requests_seconds_count`: HTTP request counts.
- `process_cpu_usage`: CPU usage.

### 5. Customize Monitoring

You can extend monitoring by adding custom metrics using the `@Timed`, `@Gauge`, or `@Counted` annotations in your Spring Boot application. These metrics will automatically be scraped by Prometheus and visualized in Grafana.

## Accessing the Application

Once the services are running, you can access the various parts of the application:

- **Frontend URL**: [http://localhost:3000](http://localhost:3000)
- **Backend API**: `http://localhost:8080` (used by the frontend)
- **Prometheus URL**: [http://localhost:9091](http://localhost:9091)
- **Grafana URL**: [http://localhost:3001](http://localhost:3001)

---
