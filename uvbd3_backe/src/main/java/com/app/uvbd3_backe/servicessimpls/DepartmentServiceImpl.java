package com.app.uvbd3_backe.servicessimpls;

import com.app.uvbd3_backe.dtos.DepartmentDTO;
import com.app.uvbd3_backe.entities.Department;
import com.app.uvbd3_backe.exceptions.ResourceNotFoundException;
import com.app.uvbd3_backe.mappers.DepartmentMapper;
import com.app.uvbd3_backe.repositories.DepartmentRepository;
import com.app.uvbd3_backe.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.mapDepartmentDTOToDepartment(departmentDTO);

        Department saveDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapDepartmentToDepartmentDTO(saveDepartment);

        // return DepartmentMapper.mapDepartmentToDepartmentDTO(departmentRepository.save(department));
    }

    @Override
    public DepartmentDTO updateDepartment(Long departmentID, DepartmentDTO departmentDTO) {
        // Verificar si ese código de departamento existe
        Department department = departmentRepository.findById(departmentID).orElseThrow(
                () -> new ResourceNotFoundException("Department not found with id: " + departmentID)
        );
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentDescription(departmentDTO.getDepartmentDescription());

        Department updateDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapDepartmentToDepartmentDTO(updateDepartment);
    }

    @Override
    public DepartmentDTO findDepartmentById(Long id) {
        // Verificar si ese código de departamento existe
        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Department not found with id: " + id)
        );
        return DepartmentMapper.mapDepartmentToDepartmentDTO(department);
    }

    @Override
    public List<DepartmentDTO> findAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(DepartmentMapper::mapDepartmentToDepartmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDepartment(Long departmentID) {
        // Verificar si ese código de departamento existe
        Department department = departmentRepository.findById(departmentID).orElseThrow(
                () -> new ResourceNotFoundException("Department not found with id: " + departmentID)
        );
        departmentRepository.delete(department);
    }
}

