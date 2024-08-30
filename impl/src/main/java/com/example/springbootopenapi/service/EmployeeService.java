package com.example.springbootopenapi.service;

import com.example.springbootopenapi.dto.CreateEmployeeDto;
import com.example.springbootopenapi.dto.EmployeeDto;
import com.example.springbootopenapi.entity.Employee;
import com.example.springbootopenapi.exception.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.springbootopenapi.repository.EmployeeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDto> getAllEmployees(Pageable pageable){
        List<Employee> content = employeeRepository.findAll(pageable).getContent();
        return content.stream().map(EmployeeService::toEmployeeDto).toList();
    }

    private static EmployeeDto toEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setDesignation(employee.getDesignation());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setJoinedOn(employee.getJoinedOn());
        employeeDto.setAddress(employee.getAddress());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setCreatedAt(employee.getCreatedAt());
        employeeDto.setUpdatedAt(employee.getUpdatedAt());

        return employeeDto;
    }

    public EmployeeDto getEmployeeById(Integer id){
        Employee employeeById = findEmployeeById(id);
        if(employeeById != null){
            return toEmployeeDto(employeeById);
        }
        throw new EmployeeNotFoundException("Employee not found");
    }

    private Employee findEmployeeById(Integer id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        log.info("Employee with id: {} doesn't exist", id);
        return null;
    }

    public EmployeeDto saveEmployee (CreateEmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setAge(employeeDto.getAge());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setJoinedOn(employeeDto.getJoinedOn());
        employee.setAddress(employeeDto.getAddress());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());

        Employee savedEmployee = employeeRepository.save(employee);

        log.info("Employee with id: {} saved successfully", employee.getId());
        return toEmployeeDto(savedEmployee);
    }

    public EmployeeDto updateEmployee (Integer id, CreateEmployeeDto employeeDto) {
        Employee employeeById = findEmployeeById(id);
        if(employeeById == null){
            throw new EmployeeNotFoundException("Employee not found");
        }

        employeeById.setFirstName(employeeDto.getFirstName());
        employeeById.setLastName(employeeDto.getLastName());
        employeeById.setAge(employeeDto.getAge());
        employeeById.setDesignation(employeeDto.getDesignation());
        employeeById.setPhoneNumber(employeeDto.getPhoneNumber());
        employeeById.setJoinedOn(employeeDto.getJoinedOn());
        employeeById.setAddress(employeeDto.getAddress());
        employeeById.setDateOfBirth(employeeDto.getDateOfBirth());
        employeeById.setUpdatedAt(LocalDateTime.now());

        employeeRepository.save(employeeById);

        log.info("Employee with id: {} updated successfully", id);
        return toEmployeeDto(employeeById);
    }

    public void deleteEmployeeById (Integer id) {
        Employee employeeById = findEmployeeById(id);
        if(employeeById == null){
            throw new EmployeeNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

}
