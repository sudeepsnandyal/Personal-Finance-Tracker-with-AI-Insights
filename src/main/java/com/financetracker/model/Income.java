package com.financetracker.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity @Table(name="incomes")
public class Income {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id") private User user;
    @Column(nullable=false) private String source;
    @Column(nullable=false,precision=15,scale=2) private BigDecimal amount;
    @Column(nullable=false) private LocalDate date;
    private String description;

    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public User getUser(){return user;} public void setUser(User u){this.user=u;}
    public String getSource(){return source;} public void setSource(String s){this.source=s;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal a){this.amount=a;}
    public LocalDate getDate(){return date;} public void setDate(LocalDate d){this.date=d;}
    public String getDescription(){return description;} public void setDescription(String d){this.description=d;}
}
