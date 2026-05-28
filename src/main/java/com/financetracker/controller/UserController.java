package com.financetracker.controller;
import com.financetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/user") @CrossOrigin(origins="*")
public class UserController {
    @Autowired private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> profile(){ return ResponseEntity.ok(userService.getCurrentUser()); }

    @PutMapping("/profile")
    public ResponseEntity<?> update(@RequestBody Map<String,Object> data){
        return ResponseEntity.ok(userService.updateProfile(data));
    }
}
