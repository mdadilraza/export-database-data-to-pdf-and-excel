package com.eidiko.report_generator.repository;

import com.eidiko.report_generator.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity,Long> {
}
