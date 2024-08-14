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

            // handle null pointer exception
            /*
            *The catch part in the provided code is designed to handle a potential `NullPointerException` that might occur when adding an `EmployeeGetDTO` object to the `employeeGetDTOList`. Here is a step-by-step explanation:

1. **Try Block**:
    - The code attempts to add an `EmployeeGetDTO` object to the `employeeGetDTOList` using `employeeGetDTOList.add(employeeGetDTO);`.

2. **Catch Block**:
    - If `employeeGetDTOList` is `null`, attempting to call `add` on it will throw a `NullPointerException`.
    - The catch block catches this exception and initializes `employeeGetDTOList` with a new list containing the single `employeeGetDTO` object: `employeeGetDTOList = List.of(employeeGetDTO);`.

3. **Purpose**:
    - This ensures that if `employeeGetDTOList` was `null` for some reason, it gets initialized properly, and the `employeeGetDTO` object is added to it.

Here is the relevant part of the code for reference:

```java
try {
    employeeGetDTOList.add(employeeGetDTO);
} catch (NullPointerException e) {
    employeeGetDTOList = List.of(employeeGetDTO);
}
```

In summary, the catch block ensures that the list is properly initialized and populated even if it was initially `null`.
             */
            try {
                employeeGetDTOList.add(employeeGetDTO);
            } catch (NullPointerException e) {
                employeeGetDTOList = new ArrayList<>(List.of(employeeGetDTO));
            }
        }
        return employeeGetDTOList;
    }

    @Override
    public Employee createEmployee(EmployeePostDTO employeePostDto) {
        Employee employee = new Employee();
        //set fields if not null
            employee.setFullName(employeePostDto.getFullName());
            employee.setEmail(employeePostDto.getEmail());
            employee.setAddress(employeePostDto.getAddress());

        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeById(Integer id, EmployeePostDTO employeePostDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee with id " + id + " not found"));

            employee.setFullName(employeePostDto.getFullName());
            employee.setEmail(employeePostDto.getEmail());
            employee.setAddress(employeePostDto.getAddress());


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
