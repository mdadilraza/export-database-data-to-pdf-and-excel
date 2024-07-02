package com.eidiko.report_generator.controller;

import com.eidiko.report_generator.entity.EmployeeEntity;
import com.eidiko.report_generator.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/report")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @PostMapping("/employee")
    public ResponseEntity<?> saveEmployee( @RequestBody EmployeeEntity employee){
        return employeeService.saveEmployee(employee);
    }



    @GetMapping("/getAll")
    public List<EmployeeEntity> getAllEmployee(){
        System.out.println(employeeService.getAllEmployee());
      return   employeeService.getAllEmployee();
    }


}
