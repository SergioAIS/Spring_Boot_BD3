package com.app.uvbd3_backe.services;

import com.app.uvbd3_backe.dtos.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(Long EmployeeID, EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
    EmployeeDTO findEmployeeById(Long id);
    List<EmployeeDTO> findAllEmployees();
}
