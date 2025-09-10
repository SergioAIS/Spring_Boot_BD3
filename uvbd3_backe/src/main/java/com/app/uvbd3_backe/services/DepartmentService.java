package com.app.uvbd3_backe.services;

import com.app.uvbd3_backe.dtos.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO updateDepartment(Long DepartmentID, DepartmentDTO departmentDTO);
    void deleteDepartment(Long id);
    DepartmentDTO findDepartmentById(Long id);
    List<DepartmentDTO> findAllDepartments();
}
