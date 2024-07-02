package com.eidiko.report_generator.service;

import com.eidiko.report_generator.entity.EmployeeEntity;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PdfService {

    @Autowired
    EmployeeService  employeeService;


    public ByteArrayInputStream createPdf(){
        List<EmployeeEntity> allEmployee = employeeService.getAllEmployee();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (Document document = new Document()) {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            // Add Content to PDF file ->
            Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
            Paragraph para = new Paragraph("Employee Record ", fontHeader);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable pdfPTable  = new PdfPTable(10);

            Stream.of("Id" ,"empId" ,"empFirstName" ,"middleName","lastName" ,
                               "empCity","state","pinCode","sal","department").
                        forEach(emp -> {
                        PdfPCell pdfPCell = new PdfPCell(new Phrase(emp));

                        Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
                        pdfPCell.setBackgroundColor(Color.CYAN);
                        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfPCell.setBorderWidth(2);

                        pdfPTable.addCell(pdfPCell);
                    });

            allEmployee.forEach(employee -> {
                PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(employee.getId())));
                pdfPTable.addCell(idCell);

                PdfPCell empIdCell = new PdfPCell(new Phrase(String.valueOf(employee.getEmpId())));
                pdfPTable.addCell(empIdCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase(employee.getEmpFirstName()));
                pdfPTable.addCell(firstNameCell);

                PdfPCell middleNameCell = new PdfPCell(new Phrase(employee.getEmpMiddleName()));
                pdfPTable.addCell(middleNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase(employee.getEmpLastName()));
                pdfPTable.addCell(lastNameCell);

                PdfPCell cityCell = new PdfPCell(new Phrase(employee.getEmpCity()));
                pdfPTable.addCell(cityCell);


                PdfPCell stateCell = new PdfPCell(new Phrase(employee.getEmpState()));
                pdfPTable.addCell(stateCell);



                PdfPCell pinCodeCell = new PdfPCell(new Phrase(String.valueOf(employee.getEmpPinCode())));
                pdfPTable.addCell(pinCodeCell);

                PdfPCell salaryCell = new PdfPCell(new Phrase(String.valueOf(employee.getEmpSalary())));
                pdfPTable.addCell(salaryCell);

                PdfPCell departmentCell = new PdfPCell(new Phrase(employee.getEmpDepartment()));
                pdfPTable.addCell(departmentCell);


            });

            document.add(pdfPTable);

        }

        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

    }
}
