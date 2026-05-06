package com.financetracker.controller;
import com.financetracker.service.AIInsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/ai") @CrossOrigin(origins="*")
public class AIController {
    @Autowired private AIInsightService service;

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody Map<String,String> body){
        String msg = body.getOrDefault("message","Give me a financial summary");
        return ResponseEntity.ok(Map.of("reply", service.generateInsight(msg)));
    }

    @GetMapping("/history")
    public ResponseEntity<?> history(){ return ResponseEntity.ok(service.getHistory()); }
}
