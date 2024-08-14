package com.example.ims.controller;

import com.example.ims.dto.EmployeeGetDTO;
import com.example.ims.dto.EmployeePostDTO;
import com.example.ims.model.Employee;
import com.example.ims.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //get employees : /employees
    @GetMapping("/employees")
    @ResponseBody
    public ResponseEntity<Object> getAllEmployees() {
        try {
            List<EmployeeGetDTO> employees = employeeService.getAllEmployees();
            if (employees.isEmpty()) {
                return new ResponseEntity<>("No employees found", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while getting employees");
        }
    }

    //post employee : /employee
    @PostMapping("/employee")
    @ResponseBody
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeePostDTO employeePostDto) {
        try {
            Employee createdEmployee = employeeService.createEmployee(employeePostDto);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while creating employee");
        }
    }

    // Delete employee :/employee
    @DeleteMapping("/employee/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteEmployeeById(@PathVariable Integer id) {
        try {
            Employee deletedEmployee = employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting employee", HttpStatus.BAD_REQUEST);
        }
    }

    // Update employee :/employee
    @PutMapping("/employee/{id}")
    public ResponseEntity<Object> updateEmployeeById(@PathVariable Integer id, @RequestBody EmployeePostDTO employeePostDto) {
        try {
            if(employeePostDto.getFullName() == null || employeePostDto.getEmail() == null || employeePostDto.getAddress() == null){
                return ResponseEntity.badRequest().body("All fields are required");
            }else{
                Employee updatedEmployee = employeeService.updateEmployeeById(id, employeePostDto);
                return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while updating employee");
        }
    }
}