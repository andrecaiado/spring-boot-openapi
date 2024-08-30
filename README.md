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
    - [Update the modules](#update-the-modules)
  - [Generate the code](#generate-the-code)
    - [Configure the code generator plugin](#configure-the-code-generator-plugin)
    - [Define the OpenAPI specification](#define-the-openapi-specification)
    - [Type mappings and import mappings](#type-mappings-and-import-mappings)
      - [The OffsetDateTime mapping](#the-offsetdatetime-mapping)
      - [The Pageable mapping](#the-pageable-mapping)
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
## Try it out with the postman collection

The Postman collection is available here: [spring-boot-template-rest-api.postman_collection.json](spring-boot-template-rest-api.postman_collection.json)

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

### Update the modules

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

### Type mappings and import mappings

Type mappings and import mappings allow for types bound to the OpenAPI Specification's types to be remapped to a user's desired types.

- The `typeMappings` property is used to defines the user's target type for a given OpenAPI Specification type.
- The `importMappings` informs the template of the type to be imported.

The `typeMappings` and `importMappings` properties are defined in the `configuration` section of the plugin configuration. In this project, the `typeMappings` and `importMappings` properties are defined in the `spec`module [pom.xml](spec%2Fpom.xml) file as shown below:

```xml
<configuration>
    <typeMappings>
        <typeMapping>OffsetDateTime=LocalDateTime</typeMapping>
        <typeMapping>Pageable=org.springframework.data.domain.Pageable</typeMapping>
    </typeMappings>
    <importMappings>
        <importMapping>LocalDateTime=java.time.LocalDateTime</importMapping>
        <importMapping>Pageable=org.springframework.data.domain.Pageable</importMapping>
    </importMappings>
</configuration>
```
The `typeMappings` and `importMappings` properties are optional. If they are not defined, the default mappings will be used.

#### The OffsetDateTime mapping

In this project, we are mapping the OffsetDateTime type to LocalDateTime.
This mapping is necessary because the OpenAPI specification uses OffsetDateTime for date-time fields however, the project uses LocalDateTime.

We also need to import the LocalDateTime class in the generated code, so we need to define the import mapping.

#### The Pageable mapping

In this project, we are mapping the Pageable type to org.springframework.data.domain.Pageable.
Because there is no Pageable type in the OpenAPI specification, we defined a custom type in the OpenAPI specification and so, we need to map it to the Spring Pageable type.

We also need to import the Pageable class in the generated code, so we need to define the import mapping.

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

In the `impl` module, create a new package to store the controllers. The controllers should then implement the interfaces that were generated based on the OpenAPI specification and that are available in the `generated-sources` directory.

**Note:**
The above-mentioned interfaces were made available to the `impl` module as it was explained in the previous section.

**Example for the EmployeeController:**

The `EmployeeApi` interface contains the methods that should be implemented by the controller. The `EmployeeController` class implements the `EmployeeApi` interface and provides the implementation for the methods.

The example below shows the EmployeeController class after specifying that it implements the EmployeeApi interface and overriding the methods. All that is left to do is to implement the methods.

```java
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController implements EmployeeApi {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @Override
  public ResponseEntity<String> deleteEmployeeById(Integer id) {
    return null;
  }

  @Override
  public ResponseEntity<List<EmployeeDto>> getAllEmployees(Pageable pageable) {
    return null;
  }

  @Override
  public ResponseEntity<EmployeeDto> getEmployeeById(Integer id) {
    return null;
  }

  @Override
  public ResponseEntity<EmployeeDto> saveEmployee(CreateEmployeeDto createEmployeeDto) {
    return null;
  }

  @Override
  public ResponseEntity<EmployeeDto> updateEmployee(Integer id, CreateEmployeeDto createEmployeeDto) {
    return null;
  }
}
```

### Accessing the DTOs

The DTOs are available in the `generated-sources` directory. To use the DTOs, import the classes in the controller classes.
