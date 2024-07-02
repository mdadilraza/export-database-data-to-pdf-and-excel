package com.eidiko.report_generator.service;

import com.eidiko.report_generator.entity.EmployeeEntity;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    EmployeeService employeeService ;

    public ByteArrayInputStream createExcel() throws IOException {

        String[] headerArr = {"Id" ,"empId" ,"empFirstName" ,"middleName","lastName" ,"empCity","state","pinCode","sal","department"};

        List<EmployeeEntity> allEmployee = employeeService.getAllEmployee();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try(Workbook workbook = new XSSFWorkbook()){

            Sheet sheet = workbook.createSheet("Employee_Record");

            Row headerRow = sheet.createRow(0);

            for(int i =0; i< headerArr.length; i++){
                headerRow.createCell(i).setCellValue(headerArr[i]);
            }

            int rowNo =1;
            for (EmployeeEntity emp: allEmployee) {
                Row row = sheet.createRow(rowNo);

                row.createCell(0).setCellValue(emp.getId());
                row.createCell(1).setCellValue(emp.getEmpId());
                row.createCell(2).setCellValue(emp.getEmpFirstName());
                row.createCell(3).setCellValue(emp.getEmpMiddleName());
                row.createCell(4).setCellValue(emp.getEmpLastName());
                row.createCell(5).setCellValue(emp.getEmpCity());
                row.createCell(6).setCellValue(emp.getEmpState());
                row.createCell(7).setCellValue(emp.getEmpPinCode());
                row.createCell(8).setCellValue(emp.getEmpSalary());
                row.createCell(9).setCellValue(emp.getEmpDepartment());

                rowNo++;//increment the row
            }

            workbook.write(byteArrayOutputStream);

        }

        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}
