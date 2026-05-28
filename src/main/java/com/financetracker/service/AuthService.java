package com.financetracker.service;
import com.financetracker.dto.*;
import com.financetracker.model.User;
import com.financetracker.repository.UserRepository;
import com.financetracker.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class AuthService {
    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtil jwtUtil;

    public JwtResponse signup(SignupRequest req){
        if(userRepo.existsByEmail(req.getEmail()))
            throw new RuntimeException("Email already registered!");
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setMonthlyIncome(req.getMonthlyIncome()!=null ? req.getMonthlyIncome() : BigDecimal.ZERO);
        userRepo.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return new JwtResponse(token, user.getName(), user.getEmail(), user.getMonthlyIncome());
    }

    public JwtResponse login(LoginRequest req){
        User user = userRepo.findByEmail(req.getEmail())
            .orElseThrow(()->new RuntimeException("Invalid email or password!"));
        if(!encoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid email or password!");
        String token = jwtUtil.generateToken(user.getEmail());
        return new JwtResponse(token, user.getName(), user.getEmail(), user.getMonthlyIncome());
    }
}
