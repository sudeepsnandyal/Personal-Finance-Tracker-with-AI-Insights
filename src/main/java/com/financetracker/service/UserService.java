package com.financetracker.service;
import com.financetracker.model.User;
import com.financetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class UserService {
    @Autowired private UserRepository userRepo;

    public User getCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
    }

    public User updateProfile(Map<String,Object> data){
        User user = getCurrentUser();
        if(data.containsKey("name")) user.setName((String)data.get("name"));
        if(data.containsKey("monthlyIncome"))
            user.setMonthlyIncome(new BigDecimal(data.get("monthlyIncome").toString()));
        return userRepo.save(user);
    }
}
