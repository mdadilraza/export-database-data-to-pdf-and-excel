package com.eidiko.report_generator.controller;

import com.eidiko.report_generator.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class PdfController {
    @Autowired
    PdfService pdfService;


    @GetMapping("/downloadPdf")
    public ResponseEntity<InputStreamResource> exportPdf(){
        ByteArrayInputStream pdf = pdfService.createPdf();
        InputStreamResource inputStreamResource = new InputStreamResource(pdf);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=emprecord.pdf")
                .contentType(MediaType.APPLICATION_PDF).body(inputStreamResource);

    }


    //upload pdf in base64
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) throws IOException {

        String pdf = pdfService.storeInPdf(file);

        return ResponseEntity.ok().body(pdf);
    }


    //fetch the base64 to pdf
    @GetMapping("/fetch/{fileName}" )

    public ResponseEntity<byte[]> fetchFromDB(@PathVariable String fileName){

        byte[] decode = pdfService.decode(fileName);

        return ResponseEntity.ok().contentType(MediaType.valueOf("application/pdf")).body(decode);
    }

}
