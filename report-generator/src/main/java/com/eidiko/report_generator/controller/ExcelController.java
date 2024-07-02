package com.eidiko.report_generator.controller;

import com.eidiko.report_generator.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @GetMapping("/downloadExcel")
    public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {

        ByteArrayInputStream excel = excelService.createExcel();

        InputStreamResource inputStreamResource = new InputStreamResource(excel);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, 
                        "attachment;filename=employee.xlsx")
                                   .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                                    .body(inputStreamResource);

    }
}
