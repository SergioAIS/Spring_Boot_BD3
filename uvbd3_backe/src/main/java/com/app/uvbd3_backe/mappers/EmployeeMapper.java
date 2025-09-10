package com.app.uvbd3_backe.mappers;

import com.app.uvbd3_backe.dtos.EmployeeDTO;
import com.app.uvbd3_backe.entities.Employee;

public class EmployeeMapper {
    //Para convertir desde la clase/entidad a la clase DTO
    public static EmployeeDTO mapEmployeeToEmployeeDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAddress(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getDepartment().getId()
        );
    }
    //Para convertir del DTO a la clase original
    public static Employee mapEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setAddress(employeeDTO.getAddress());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        return employee;
    }
}
