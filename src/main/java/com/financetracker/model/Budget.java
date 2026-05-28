package com.financetracker.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity @Table(name="budgets")
public class Budget {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id") private User user;
    @Column(nullable=false) private String category;
    @Column(nullable=false,precision=15,scale=2) private BigDecimal limitAmount;
    @Column(nullable=false) private int month;
    @Column(nullable=false) private int year;

    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public User getUser(){return user;} public void setUser(User u){this.user=u;}
    public String getCategory(){return category;} public void setCategory(String c){this.category=c;}
    public BigDecimal getLimitAmount(){return limitAmount;} public void setLimitAmount(BigDecimal l){this.limitAmount=l;}
    public int getMonth(){return month;} public void setMonth(int m){this.month=m;}
    public int getYear(){return year;} public void setYear(int y){this.year=y;}
}
