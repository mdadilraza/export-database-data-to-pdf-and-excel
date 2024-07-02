package com.eidiko.report_generator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private long empId;

    private String empFirstName;

    private String empMiddleName;

    private String empLastName;

    private String empCity;
    private String empState;
    private int empPinCode;

    private double empSalary;

    private String empDepartment;
}
