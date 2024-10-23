
---

# Cars Backend (Spring Boot)

This is the backend part of the **Voiture Shop App**, built with **Spring Boot**, **Java 17**, and Dockerized for easy deployment. The project integrates **Spring Boot Actuator**, **Prometheus**, and **Grafana** for monitoring and performance visualization. This guide provides steps for setting up, building, running, and monitoring the backend service using Docker Compose.

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

### 2. Add Dependencies

To integrate **Prometheus**, **Spring Boot Actuator**, and **Grafana** for monitoring, you need to add the following dependencies to your `pom.xml`:

```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

These dependencies allow Prometheus to scrape metrics from your Spring Boot application through the Actuator.

### 3. Build the Backend with Maven

Before building the Docker image, ensure the project is compiled by running the following Maven command:

```bash
mvn clean package -DskipTests
```

> ⚠️ **Note**: The tests are skipped here using the `-DskipTests` flag to speed up the build process. You can remove this flag if you want to run the tests.

### 4. Build the Docker Image

Once the project is built, create the Docker image for the backend service using:

```bash
docker build -t backend-spring .
```

This will generate a Docker image for the backend using the `Dockerfile`.

### 5. Run Docker Compose

Now you can run all the services (backend, frontend, PostgreSQL, Prometheus, Grafana) defined in the Docker Compose file:

```bash
docker-compose up -d
```

This command will start the services in detached mode, including the backend with PostgreSQL, the React frontend, Prometheus for scraping metrics, and Grafana for monitoring.

### 6. Verify Services

Once Docker Compose is up, verify that all services are running:

```bash
docker-compose ps
```

You should see `backend`, `postgres`, `frontend`, `prometheus`, and `grafana` running.

Sure! Here’s the updated section with your request about consulting Prometheus' state through **Status > Targets**, as well as a placeholder for your example image:

---

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

#### Check the Status of Prometheus Targets

To consult the state of your Prometheus, you can navigate to:

1. **Status** → **Targets**
2. Here, you'll see all the monitored endpoints. If everything is working correctly, the status will show **UP**; otherwise, it will show **DOWN**.

This is an easy way to verify whether your services are being scraped correctly by Prometheus.

> *Here is an example image from my project showing the state of the Prometheus targets:*

![image](https://github.com/user-attachments/assets/54386bdb-e96c-4bed-90a0-d358fb06a872)

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

For a more sophisticated dashboard, you can download a JSON template from [Actuator JSON Dashboard](https://grafana.com/grafana/dashboards) and import it into Grafana:

- Navigate to **Create** → **Import**.
- Upload the JSON file and map it to your Prometheus data source.

### 5. Customize Monitoring

You can extend monitoring by adding custom metrics using the `@Timed`, `@Gauge`, or `@Counted` annotations in your Spring Boot application. These metrics will automatically be scraped by Prometheus and visualized in Grafana.

For even more detailed dashboards, you can explore and install various plugins and templates from the Grafana marketplace to enhance the visualization of your metrics.

### Dashboard Screenshot
![image](https://github.com/user-attachments/assets/eeef8b95-c31d-4224-8486-c9cb0941d711)



---

## Accessing the Application

Once the services are running, you can access the various parts of the application:

- **Frontend URL**: [http://localhost:3000](http://localhost:3000)
- **Backend API**: `http://localhost:8080` (used by the frontend)
- **Prometheus URL**: [http://localhost:9091](http://localhost:9091)
- **Grafana URL**: [http://localhost:3001](http://localhost:3001)

---

