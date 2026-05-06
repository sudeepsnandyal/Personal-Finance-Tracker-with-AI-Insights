package com.financetracker.controller;
import com.financetracker.dto.*;
import com.financetracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/auth") @CrossOrigin(origins="*")
public class AuthController {
    @Autowired private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req){
        try{ return ResponseEntity.ok(authService.signup(req)); }
        catch(Exception e){ return ResponseEntity.badRequest().body(Map.of("error",e.getMessage())); }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req){
        try{ return ResponseEntity.ok(authService.login(req)); }
        catch(Exception e){ return ResponseEntity.badRequest().body(Map.of("error",e.getMessage())); }
    }
}
