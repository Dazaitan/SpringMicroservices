package com.microservice.export.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
public class HealthCheckController {
    @GetMapping("/actuator/health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }
}
