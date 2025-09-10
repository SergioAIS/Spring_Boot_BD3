package com.app.uvbd3_backe.controllers;

import com.app.uvbd3_backe.dtos.EmployeeDTO;
import com.app.uvbd3_backe.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Crear un empleado
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO saveEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }

    // Actualizar un empleado
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO saveEmployee = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(saveEmployee);
    }

    // Obtener un empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    // Listar todos los empleados
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeDTOs = employeeService.findAllEmployees();
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }

    // Eliminar un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
