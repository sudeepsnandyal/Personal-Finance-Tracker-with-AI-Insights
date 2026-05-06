package com.financetracker.dto;
import java.math.BigDecimal;
public class JwtResponse {
    private String token; private String name; private String email; private BigDecimal monthlyIncome;
    public JwtResponse(String token, String name, String email, BigDecimal monthlyIncome){
        this.token=token; this.name=name; this.email=email; this.monthlyIncome=monthlyIncome;
    }
    public String getToken(){return token;}
    public String getName(){return name;}
    public String getEmail(){return email;}
    public BigDecimal getMonthlyIncome(){return monthlyIncome;}
}
