package com.example.springbootopenapi.controller;

import com.example.springbootopenapi.dto.CreateEmployeeDto;
import com.example.springbootopenapi.dto.EmployeeDto;
import com.example.springbootopenapi.service.EmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController implements EmployeeApi {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(Integer id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().body("Employee deleted successfully");
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(Pageable pageable) {
        return ResponseEntity.ok().body(employeeService.getAllEmployees(pageable));
    }

    @Override
    public ResponseEntity<EmployeeDto> getEmployeeById(Integer id) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
    }

    @Override
    public ResponseEntity<EmployeeDto> saveEmployee(CreateEmployeeDto createEmployeeDto) {
        return new ResponseEntity<>(employeeService.saveEmployee(createEmployeeDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EmployeeDto> updateEmployee(Integer id, CreateEmployeeDto createEmployeeDto) {
        return ResponseEntity.ok().body(employeeService.updateEmployee(id, createEmployeeDto));
    }
}
