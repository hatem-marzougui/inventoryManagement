package com.example.ims.controller;

import com.example.ims.dto.EmployeeGetDTO;
import com.example.ims.dto.EmployeePostDTO;
import com.example.ims.model.Employee;
import com.example.ims.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//unit test for EmployeeController


@WebMvcTest(EmployeeController.class)
public class EmployeeControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    List<EmployeeGetDTO> employees = new ArrayList<>();
    EmployeePostDTO employeePost = new EmployeePostDTO();

    @BeforeEach
    void setUp() {
        EmployeeGetDTO employee = new EmployeeGetDTO();
        employee.setId(1);
        employee.setFullName("John Doe");
        employee.setEmail("email@mail.com");
        employee.setAddress("1234 Main St");

        EmployeeGetDTO employee2 = new EmployeeGetDTO();
        employee2.setId(2);
        employee2.setFullName("Jane Doe");
        employee2.setEmail("jane@mail.com");
        employee2.setAddress("5678 Main St");

        employees.add(employee);
        employees.add(employee2);

    }
    //unit test for getAllEmployees
    @Test
    void getAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(employees);
        mockMvc.perform(get("/employees")).andExpect(status().isOk());
    }

    //unit test for createEmployee
    @Test
    void createEmployee() throws Exception {
        // Set up the employeePost object
        employeePost.setFullName("Jane Doe");
        employeePost.setEmail("jane.doe@mail.com");
        employeePost.setAddress("5678 Main St");

        // Create a new Employee object to be returned by the mock service
        Employee createdEmployee = new Employee();
        createdEmployee.setId(1);
        createdEmployee.setFullName("Jane Doe");
        createdEmployee.setEmail("jane.doe@mail.com");
        createdEmployee.setAddress("5678 Main St");

        // Mock the employeeService.createEmployee method
        when(employeeService.createEmployee(employeePost)).thenReturn(createdEmployee);

        // Perform the POST request
        mockMvc.perform(post("/employee")
                        .contentType("application/json")
                        .content("{\"fullName\":\"Jane Doe\",\"email\":\"jane.doe@mail.com\",\"address\":\"5678 Main St\"}"))
                .andExpect(status().isCreated());
    }

    // unit test for updateEmployee
    @Test
    void updateEmployee() throws Exception {
        // Set up the employeePost object
        employeePost.setFullName("Jane Doe");
        employeePost.setEmail("jane.doe@mail.com");
        employeePost.setAddress("5678 Main St");

        // Create a new Employee object to be returned by the mock service
        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1);
        updatedEmployee.setFullName("Dane Doe");
        updatedEmployee.setEmail("dane.doe@mail.com");
        updatedEmployee.setAddress("5678 Main St");

        // Mock the employeeService.updateEmployee method
        when(employeeService.updateEmployeeById(1, employeePost)).thenReturn(updatedEmployee);

        // Perform the PUT request
        mockMvc.perform(put("/employee/1")
                        .contentType("application/json")
                        .content("{\"fullName\":\"Dane Doe\",\"email\":\"test@test@fr\",\"address\":\"5678 Main St\"}"))
                .andExpect(status().isOk());

    }

}