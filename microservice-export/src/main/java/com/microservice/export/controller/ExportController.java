package com.microservice.export.controller;

import com.microservice.export.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/export")
public class ExportController {
    @Autowired
    private ExportService exportService;

//    @GetMapping("/books")
//    public ResponseEntity<String> exportBooks(@RequestParam String filename) {
//        /try {
//            exportService.exportBooksToCSV(filename);
//            return ResponseEntity.ok("Export successful to " + filename);
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body("Error during export: " + e.getMessage());
//        }
//    }
    @GetMapping("/books")
    public String export(){
        return "Accedío al endpoint";
    }
}
