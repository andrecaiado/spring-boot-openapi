# Spring Boot OpenAPI demo project

This is a Spring Boot demo project that implements API documentation using OpenApi specification and API-first approach.

# Contents

- [Features](#features)
- [Requirements](#requirements)
- [Getting Started](#getting-started)
  - [Installation](#installation)

# Features

This project's main features are:

- Generates the controllers and DTOs (as Java Records) based on the OpenAPI specification.
- Generates OpenAPI documentation for the API.

**Note:**

This readme file will focus on the OpenAPI documentation and API-first implementation. For more details about the other features of this project, please refer to the [Spring Boot Template project](https://github.com/andrecaiado/spring-boot-template) that was used as a base for this project.

# Requirements

- Java 17
- Docker
- Docker Compose

# Getting Started

This section provides a step-by-step guide on how to run the project.

## Installation

1. Clone the repository by executing the following command:

```shell
git clone git@github.com:andrecaiado/spring-boot-openapi.git
```

2. Navigate into the project directory:

```
cd your-repository-name
```

3. Install the dependencies and generate the code from the OpenAPI specification by executing the following command:

```shell
./mvnw clean install
```

4. Run the application by executing the following command:

```shell 
./mvnw spring-boot:run
```
