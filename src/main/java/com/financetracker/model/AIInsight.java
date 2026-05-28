package com.financetracker.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="ai_insights")
public class AIInsight {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id") private User user;
    @Column(columnDefinition="TEXT") private String insightText;
    @Column(columnDefinition="TEXT") private String userMessage;
    private LocalDateTime generatedAt;

    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public User getUser(){return user;} public void setUser(User u){this.user=u;}
    public String getInsightText(){return insightText;} public void setInsightText(String t){this.insightText=t;}
    public String getUserMessage(){return userMessage;} public void setUserMessage(String m){this.userMessage=m;}
    public LocalDateTime getGeneratedAt(){return generatedAt;} public void setGeneratedAt(LocalDateTime d){this.generatedAt=d;}
}
