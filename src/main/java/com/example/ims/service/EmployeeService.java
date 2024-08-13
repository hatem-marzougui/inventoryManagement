package com.example.ims.service;


import com.example.ims.dto.EmployeeGetDTO;
import com.example.ims.dto.EmployeePostDTO;
import com.example.ims.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeGetDTO> getAllEmployees();
    Employee createEmployee(EmployeePostDTO employeePostDto);
    Employee updateEmployeeById(Integer id, EmployeePostDTO employeePostDto);
    Employee deleteEmployeeById(Integer id);
}
