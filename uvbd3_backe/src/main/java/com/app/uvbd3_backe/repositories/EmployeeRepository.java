package com.app.uvbd3_backe.repositories;

import com.app.uvbd3_backe.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
