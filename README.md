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

# Requirements (UPDATE)

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

# Implementation

This section provides a brief explanation of the implementation details of the project.

## Dependencies

The dependencies used to implement the OpenAPI documentation and API-first approach are:

- [springdoc-openapi](https://springdoc.org/): Library that generates the OpenAPI documentation based on the Spring Boot application.

## Project structure

To implement the API-first approach, the project is divided into the following modules:

- **spec**: Contains the OpenAPI specification file.
- **impl**: Contains the project's implementation, including the generated controllers and DTOs based on the OpenAPI specification.

### Create the modules

The modules were created using IntelliJ. The steps to create a new module are:

1. Right-click on the project's root directory.
2. Select "New" -> "Module".
3. Provide a name for the module and click "Finish".

After creating the first module, one can delete the newly created module `src` folder and move the project's existing `src` directory to the new module.

### Update the `impl` module's `pom.xml` file

To update the `pom.xml` file, move the dependencies and plugins from the project's root `pom.xml` file to the `impl` module's `pom.xml` file.
