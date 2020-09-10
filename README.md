# Github Actions - Java App

## Summary

This repo demonstrates CI/CD using Github Actions with a Java Application.

## Technologies

- Java 8
- Spring Boot
    - Spring Web
    - Spring Data JPA
- Lombok

## Local Development

### Build locally

```bash
./mvnw clean install
```

### Run locally

```bash
./mvnw spring-boot:run
```

### Build Docker Image

```bash
docker build -t github-actions-java-app:latest .
```

### Run Docker Container

```bash
docker run --rm --name github-actions-java-app -d -p 8080:8080 github-actions-java-app:latest
```