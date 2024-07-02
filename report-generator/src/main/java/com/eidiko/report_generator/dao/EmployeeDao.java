package com.eidiko.report_generator.dao;

import com.eidiko.report_generator.entity.EmployeeEntity;
import com.eidiko.report_generator.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {

    @Autowired
    EmployeeRepo employeeRepo;

    public EmployeeEntity saveEmployee(EmployeeEntity employee){

       return employeeRepo.save(employee);
    }


    public List<EmployeeEntity> getAllEmployee(){
       return employeeRepo.findAll();
    }
}
