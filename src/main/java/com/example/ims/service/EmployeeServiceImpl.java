package com.example.ims.service;

import com.example.ims.dao.EmployeeRepository;
import com.example.ims.dto.EmployeeGetDTO;
import com.example.ims.dto.EmployeePostDTO;
import com.example.ims.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<EmployeeGetDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        //initialize employeeGetDTOList with empty list
        List<EmployeeGetDTO> employeeGetDTOList = new ArrayList<>();
        //convert to DTO
        // map employees to DTO List
        for (Employee employee : employees) {
            EmployeeGetDTO employeeGetDTO = new EmployeeGetDTO();
            employeeGetDTO.setId(employee.getId());
            employeeGetDTO.setFullName(employee.getFullName());
            employeeGetDTO.setEmail(employee.getEmail());
            employeeGetDTO.setAddress(employee.getAddress());
            for(int i = 0; i < employee.getTransactions().size(); i++){
                employeeGetDTO.getTransactions().add(employee.getTransactions().get(i).getId());
            }
            // habdle null pointer exception
            /*
            This code attempts to add an EmployeeGetDTO object to the employeeGetDTOList.
            If employeeGetDTOList is null, a NullPointerException will be thrown.
            The catch block handles this exception by initializing employeeGetDTOList with a single-element list containing the employeeGetDTO object.
                Pseudocode:
                Try to add employeeGetDTO to employeeGetDTOList.
                If employeeGetDTOList is null, catch the NullPointerException.
                Initialize employeeGetDTOList with a list containing employeeGetDTO.
             */
            try {
                employeeGetDTOList.add(employeeGetDTO);
            } catch (NullPointerException e) {
                employeeGetDTOList = List.of(employeeGetDTO);
            }
        }
        return employeeGetDTOList;
    }

    @Override
    public Employee createEmployee(EmployeePostDTO employeePostDto) {
        Employee employee = new Employee();
        //set fields if not null
        if (employeePostDto.getFullName() != null && employeePostDto.getEmail() != null && employeePostDto.getAddress() != null) {
            employee.setFullName(employeePostDto.getFullName());
            employee.setEmail(employeePostDto.getEmail());
            employee.setAddress(employeePostDto.getAddress());
        }else {
            throw new IllegalArgumentException("All fields are required");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeById(Integer id, EmployeePostDTO employeePostDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee with id " + id + " not found"));

        // Update fields
       if (employeePostDto.getFullName() != null && employeePostDto.getEmail() != null && employeePostDto.getAddress() != null) {
            employee.setFullName(employeePostDto.getFullName());
            employee.setEmail(employeePostDto.getEmail());
            employee.setAddress(employeePostDto.getAddress());
        }else {
           throw new IllegalArgumentException("All fields are required");
       }

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee deleteEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee with id " + id + " not found"));

        employeeRepository.delete(employee);
        return employee;
    }
}
