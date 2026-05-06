package com.financetracker.repository;
import com.financetracker.model.AIInsight;
import com.financetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AIInsightRepository extends JpaRepository<AIInsight,Long> {
    List<AIInsight> findByUserOrderByGeneratedAtDesc(User user);
}
