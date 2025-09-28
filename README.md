# Java Link Shortener

![Java CI with Maven](https://github.com/ozhadykov/java-link-shortener/actions/workflows/ci.yml/badge.svg)

A robust, full-stack link shortener application built with Java and Spring Boot. This project demonstrates modern backend development practices, including a clean REST API, a deterministic ID-to-link hashing strategy, and a complete CI pipeline for automated testing.

## Features

- **Collision-Free Short Links:** Uses `hashids` to encode database IDs into unique, non-sequential short codes, eliminating the risk of collisions.
- **REST API:** A clean, well-defined API for creating and retrieving links.
- **Backend Validation:** Robust server-side validation for incoming URLs.
- **Custom Error Handling:** Provides user-friendly custom 404 pages and specific JSON error responses for the API.
- **Unit Tested:** Core business logic is verified with a suite of unit tests using JUnit 5 and Mockito.
- **CI/CD Pipeline:** Includes a GitHub Actions workflow that automatically tests every push and pull request, ensuring code quality and stability.
- **Dockerized Database:** Utilizes Docker Compose for easy and consistent local development database setup.

## Tech Stack

- **Backend:** Java 21, Spring Boot (Web, Data JPA, Validation)
- **Database:** PostgreSQL
- **Build & Dependencies:** Maven
- **ID Hashing:** [Hashids](https://hashids.org/java/)
- **Testing:** JUnit 5, Mockito, AssertJ
- **CI/CD:** GitHub Actions
- **Containerization:** Docker, Docker Compose

## Getting Started

To run this project on your local machine, you will need Java 21 (or higher) and Docker installed.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/ozhadykov/java-link-shortener.git
    cd java-link-shortener
    ```

2.  **Start the Database:**
    Use Docker Compose to start the PostgreSQL database container in the background.
    ```bash
    docker-compose up -d
    ```

3.  **Run the Application:**
    Use the Maven wrapper to run the Spring Boot application.
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Access the Application:**
    The frontend is available at `http://localhost:8080`.

## API Endpoints

### Create a Short Link

- **Endpoint:** `POST /r/create-link`
- **Request Body:**
  ```json
  {
    "link": "https://your-long-url.com/goes/here"
  }
  ```
- **Success Response (201 CREATED):**
  ```json
  {
    "shortUrl": "http://localhost:8080/r/gYvA7"
  }
  ```
- **Error Response (400 BAD REQUEST):**
  ```json
  {
    "link": "Invalid URL format"
  }
  ```

### Redirect to Original URL

- **Endpoint:** `GET /r/{shortCode}`
- **Example:** `GET http://localhost:8080/r/gYvA7`
- **Response:** An HTTP 302 Redirect to the original URL.

## Running Tests

To run the complete test suite, execute the following command:

```bash
./mvnw test
```

## Future Improvements

- **User Authentication:** Implement user accounts to allow users to manage their own links.
- **Integration Testing:** Add a suite of integration tests to verify the interactions between the controller, service, and database layers.
- **API Documentation:** Integrate `springdoc-openapi` to generate a Swagger UI for the REST API.