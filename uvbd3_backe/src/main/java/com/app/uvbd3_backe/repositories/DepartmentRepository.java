package com.app.uvbd3_backe.repositories;

import com.app.uvbd3_backe.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
