package com.financetracker.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name="users")
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String name;
    @Column(nullable=false,unique=true) private String email;
    @JsonIgnore
    @Column(nullable=false) private String password;
    @Column(precision=15,scale=2) private BigDecimal monthlyIncome = BigDecimal.ZERO;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String n){this.name=n;}
    public String getEmail(){return email;} public void setEmail(String e){this.email=e;}
    public String getPassword(){return password;} public void setPassword(String p){this.password=p;}
    public BigDecimal getMonthlyIncome(){return monthlyIncome;} public void setMonthlyIncome(BigDecimal m){this.monthlyIncome=m;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime c){this.createdAt=c;}
}
