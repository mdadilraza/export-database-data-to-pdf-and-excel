package com.eidiko.report_generator.controller;

import com.eidiko.report_generator.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class PdfController {
    @Autowired
    PdfService pdfService;
    @GetMapping("/downloadPdf")
    public ResponseEntity<InputStreamResource> exportPdf(){
        ByteArrayInputStream pdf = pdfService.createPdf();
        InputStreamResource inputStreamResource = new InputStreamResource(pdf);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"inline;filename=emprecord.pdf")
                .contentType(MediaType.APPLICATION_PDF).body(inputStreamResource);


    }
}
