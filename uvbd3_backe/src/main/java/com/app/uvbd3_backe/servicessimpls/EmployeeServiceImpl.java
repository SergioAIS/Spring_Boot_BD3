package com.app.uvbd3_backe.servicessimpls;

import com.app.uvbd3_backe.entities.Department;
import com.app.uvbd3_backe.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;
import com.app.uvbd3_backe.dtos.EmployeeDTO;
import com.app.uvbd3_backe.entities.Employee;
import com.app.uvbd3_backe.exceptions.ResourceNotFoundException;
import com.app.uvbd3_backe.mappers.EmployeeMapper;
import com.app.uvbd3_backe.repositories.EmployeeRepository;
import com.app.uvbd3_backe.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.mapEmployeeDTOToEmployee(employeeDTO);

        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + employeeDTO.getDepartmentId()));

        employee.setDepartment(department);

        Employee saveEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapEmployeeToEmployeeDTO(saveEmployee);

        //return EmployeeMapper.mapEmployeeToEmployeeDTO(employeeRepository.save(employee));
    }

//    @Override
//    public EmployeeDTO getEmployeeById(Long employeeId) {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
//        return EmployeeMapper.mapEmployeeToEmployeeDTO(employee);
//    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeID, EmployeeDTO updatedEmployee) {
        //Verificar si ese código de empleado existe
        Employee employee = employeeRepository.findById(employeeID).orElseThrow(
                ()-> new ResourceNotFoundException("Employee not found with id: " + employeeID)
        );
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setPhone(updatedEmployee.getPhone());
        employee.setAddress(updatedEmployee.getAddress());

        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department is not exists with id: " + updatedEmployee.getDepartmentId()));

        Employee  updateEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapEmployeeToEmployeeDTO(updateEmployee);
    }

    @Override
    public EmployeeDTO findEmployeeById(Long id) {
        //Verificar si ese código de empleado existe
        Employee employee = employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee not found with id: " + id)
        );
        return EmployeeMapper.mapEmployeeToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapEmployeeToEmployeeDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long employeeID) {
        //Verificar si ese código de empleado existe
        Employee employee = employeeRepository.findById(employeeID).orElseThrow(
                ()-> new ResourceNotFoundException("Employee not found with id: " + employeeID)
        );
        employeeRepository.delete(employee);
    }
}
