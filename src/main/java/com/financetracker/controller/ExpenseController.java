package com.financetracker.controller;
import com.financetracker.model.Expense;
import com.financetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/expenses") @CrossOrigin(origins="*")
public class ExpenseController {
    @Autowired private ExpenseService service;

    @GetMapping public ResponseEntity<?> getAll(){ return ResponseEntity.ok(service.getAll()); }
    @GetMapping("/chart") public ResponseEntity<?> chart(){ return ResponseEntity.ok(service.getChartData()); }
    @GetMapping("/trend") public ResponseEntity<?> trend(){ return ResponseEntity.ok(service.getMonthlyTrend()); }
    @PostMapping public ResponseEntity<?> add(@RequestBody Expense e){ return ResponseEntity.ok(service.add(e)); }
    @PutMapping("/{id}") public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Expense e){ return ResponseEntity.ok(service.update(id,e)); }
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.ok(Map.of("message","Deleted")); }
}
