package com.eidiko.report_generator.service;

import com.eidiko.report_generator.dao.EmployeeDao;
import com.eidiko.report_generator.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    public ResponseEntity<?> saveEmployee(EmployeeEntity employee) {
        employeeDao.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("All employee Inserted into database");
    }


    public List<EmployeeEntity> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }
}
