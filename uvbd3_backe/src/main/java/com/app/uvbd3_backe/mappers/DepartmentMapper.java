package com.app.uvbd3_backe.mappers;

import com.app.uvbd3_backe.dtos.DepartmentDTO;
import com.app.uvbd3_backe.entities.Department;

public class DepartmentMapper {
    // Para convertir desde la clase/entidad a la clase dto
    public static DepartmentDTO mapDepartmentToDepartmentDTO(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription()
        );
    }

    // Para convertir del DTO a la clase original
    public static Department mapDepartmentDTOToDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(departmentDTO.getId());
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentDescription(departmentDTO.getDepartmentDescription());
        return department;
    }
}

