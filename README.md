# Cloud-Native Link Shortener 🚀

This is a simple link shortener application built with Java and Spring Boot. The primary goal of this project is educational—to gain hands-on experience with modern cloud-native technologies and best practices.

It serves as a practical exercise in building a full-stack application, containerizing it with **Docker**  , defining infrastructure as code with **Terraform**  , and automating the entire deployment process to **AWS** with **GitHub Actions** .

---

## Tech Stack 🛠️

* **Backend:** Java 21, Spring Boot (Spring Web, Spring Data JPA)  
* **Database:** PostgreSQL  
* **Containerization:** Docker  
* **Infrastructure as Code (IaC):** Terraform 
* **Cloud Provider:** AWS (Fargate, RDS)  
* **CI/CD:** GitHub Actions 

---

## Project Goals ✅

This project aims to demonstrate a solid understanding of the full software development lifecycle, including:

* Implementing a robust REST API with Spring Boot.
* Containerizing the application using Docker for portability and consistent environments.
* Defining and managing all required cloud infrastructure on AWS programmatically using Terraform.
* Setting up a complete CI/CD pipeline with GitHub Actions for automated builds, testing, and deployments.  
* Following best practices for project structure, development workflows, and cloud architecture.

---

## Getting Started (Local Development)

To run this project on your local machine, you'll need to have Docker and Docker Compose installed.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/ozhadykov/java-spring-link-shortener
    ```

2.  **Navigate to the project directory:**
    ```bash
    cd java-spring-link-shortener
    ```

3.  **Run the application using Docker Compose:**
    This command will build the Spring Boot application image and start both the application and the PostgreSQL database containers.
    ```bash
    docker compose up --build
    ```

4.  **Access the application:**
    Once the containers are running, the API will be available at `http://localhost:8080`.

---

## Project Status

🚧 **Project Status: In Progress** 🚧
