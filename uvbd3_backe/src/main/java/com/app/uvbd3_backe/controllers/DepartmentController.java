package com.app.uvbd3_backe.controllers;
import com.app.uvbd3_backe.dtos.DepartmentDTO;
import com.app.uvbd3_backe.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin("*")


public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Crear un departamento
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO saveDepartment = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }

    // Actualizar un departamento
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO saveDepartment = departmentService.updateDepartment(id, departmentDTO);
        return ResponseEntity.ok(saveDepartment);
    }

    // Obtener un departamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long id) {
        DepartmentDTO departmentDTO = departmentService.findDepartmentById(id);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    // Listar todos los departamentos
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departmentDTOs = departmentService.findAllDepartments();
        return new ResponseEntity<>(departmentDTOs, HttpStatus.OK);
    }

    // Eliminar un departamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

