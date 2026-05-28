package com.financetracker.controller;
import com.financetracker.model.Budget;
import com.financetracker.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/budgets") @CrossOrigin(origins="*")
public class BudgetController {
    @Autowired private BudgetService service;

    @GetMapping public ResponseEntity<?> getAll(){ return ResponseEntity.ok(service.getAllWithStatus()); }
    @GetMapping("/alerts") public ResponseEntity<?> alerts(){ return ResponseEntity.ok(service.getAlerts()); }
    @GetMapping("/predict") public ResponseEntity<?> predict(){ return ResponseEntity.ok(service.predictNextMonth()); }
    @PostMapping public ResponseEntity<?> add(@RequestBody Budget b){ return ResponseEntity.ok(service.add(b)); }
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.ok(Map.of("message","Deleted")); }
}
