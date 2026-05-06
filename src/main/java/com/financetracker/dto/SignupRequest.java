package com.financetracker.dto;
import java.math.BigDecimal;
public class SignupRequest {
    private String name; private String email; private String password; private BigDecimal monthlyIncome;
    public String getName(){return name;} public void setName(String n){this.name=n;}
    public String getEmail(){return email;} public void setEmail(String e){this.email=e;}
    public String getPassword(){return password;} public void setPassword(String p){this.password=p;}
    public BigDecimal getMonthlyIncome(){return monthlyIncome;} public void setMonthlyIncome(BigDecimal m){this.monthlyIncome=m;}
}
