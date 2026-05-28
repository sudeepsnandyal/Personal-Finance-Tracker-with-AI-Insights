package com.financetracker.controller;
import com.financetracker.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/dashboard") @CrossOrigin(origins="*")
public class DashboardController {
    @Autowired private DashboardService service;
    @GetMapping public ResponseEntity<?> summary(){ return ResponseEntity.ok(service.getSummary()); }
}
