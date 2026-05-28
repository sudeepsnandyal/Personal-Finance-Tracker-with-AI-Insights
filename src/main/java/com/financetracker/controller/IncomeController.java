package com.financetracker.controller;
import com.financetracker.model.Income;
import com.financetracker.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/income") @CrossOrigin(origins="*")
public class IncomeController {
    @Autowired private IncomeService service;

    @GetMapping public ResponseEntity<?> getAll(){ return ResponseEntity.ok(service.getAll()); }
    @GetMapping("/summary") public ResponseEntity<?> summary(){ return ResponseEntity.ok(service.getSummary()); }
    @PostMapping public ResponseEntity<?> add(@RequestBody Income i){ return ResponseEntity.ok(service.add(i)); }
    @PutMapping("/{id}") public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Income i){ return ResponseEntity.ok(service.update(id,i)); }
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.ok(Map.of("message","Deleted")); }
}
