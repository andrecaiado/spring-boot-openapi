# Spring Boot OpenAPI demo project

This is a Spring Boot demo project that implements API documentation using OpenApi specification and API-first approach.

# Contents

- [Features](#features)
- [Requirements](#requirements)
- [Getting Started](#getting-started)
  - [Installation](#installation)
- [Implementation](#implementation)
  - [Create the project structure](#create-the-project-structure)
    - [Create the modules](#create-the-modules)
    - [Update the `impl` module's `pom.xml` file](#update-the-impl-modules-pomxml-file)
  - [Generate the code](#generate-the-code)
    - [Configure the code generator plugin](#configure-the-code-generator-plugin)
    - [Define the OpenAPI specification](#define-the-openapi-specification)
    - [Generate the code](#generate-the-code)
  - [Use the generated code in the project](#use-the-generated-code-in-the-project)
    - [Accessing the generated resources](#accessing-the-generated-resources)
    - [Implement the controllers](#implement-the-controllers)
    - [Accessing the DTOs](#accessing-the-dtos)

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

## Create the project structure

To implement the API-first approach, the project is divided into the following modules:

- **spec**: Contains the OpenAPI specification file.
- **impl**: Contains the project's implementation, including the generated controllers and DTOs based on the OpenAPI specification.

### Create the modules

The modules were created using IntelliJ. The steps to create a new module are:

1. Right-click on the project's root directory.
2. Select "New" -> "Module".
3. Provide a name for the module and click "Finish".

After creating the `impl` module, delete this module's `src` folder and move the project's existing `src` directory to the new module.

After creating the `spec` module, one can delete this module's `java` folder.

### Update the `impl` module's `pom.xml` file

To update the `pom.xml` file, move the dependencies and plugins from the project's root `pom.xml` file to the `impl` module's `pom.xml` file.

## Generate the code

This section provides a step-by-step guide on how to generate the controllers and DTOs based on the OpenAPI specification.

### Configure the code generator plugin

To generate the controllers and DTOs based on the OpenAPI specification, the `openapi-generator-maven-plugin` plugin is used. The plugin configuration is defined in the `spec` module's [pom.xml](spec%2Fpom.xml) file.

To have a better understanding of the plugin configuration, please refer to the [openapi-generator-maven-plugin documentation](https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-maven-plugin).

### Define the OpenAPI specification

Create a `yaml` file in the `spec` module's `src/main/resources` directory. In this project, the OpenAPI specification is defined in the [openapi.yaml](spec%2Fsrc%2Fmain%2Fresources%2Fopenapi.yaml) file.

The OpenAPI specification file should contain the API's endpoints, request and response bodies, and other relevant information.

The OpenAPI specification file can be created manually or using a tool like [Swagger Editor](https://editor.swagger.io/).

Please refer to the [OpenAPI Specification documentation](https://swagger.io/docs/specification/about/) for more details on how to define the API's endpoints and models.

### Generate the code

To generate the controllers and DTOs based on the OpenAPI specification, execute the following command:

```shell
./mvnw clean compile
```

The plugin itself is defined to be triggered when the `compile` phase is triggered.

After executing the command, the generated code will be available in the `target` directory of the `spec` module:
- Controllers: `target/generated-sources/openapi/src/main/java/com/example/springbootopenapi/controller`
- DTOs: `target/generated-sources/openapi/src/main/java/com/example/springbootopenapi/dto`

These locations were defined in the `openapi-generator-maven-plugin` plugin configuration.

## Use the generated code in the project

This section provides a brief explanation of how to use the generated code in the project.

### Accessing the generated resources

In order to access the generated resources, the `impl` module's [pom.xml](impl%2Fpom.xml) file should be updated to include the `spec` module as a dependency.

First, we need to configure the dependency management in the main [pom.xml](pom.xml) file:

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>com.example</groupId>
      <artifactId>spec</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
  </dependencies>
</dependencyManagement>
```

Then, we need to add the `spec` module as a dependency in the `impl` module's [pom.xml](impl%2Fpom.xml) file:

```xml
<dependencies>
  <dependency>
    <groupId>com.example</groupId>
    <artifactId>spec</artifactId>
    <version>0.0.1-SNAPSHOT</version>
  </dependency>
</dependencies>
```

### Implement the controllers

### Accessing the DTOs
