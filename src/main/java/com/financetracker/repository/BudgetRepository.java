package com.financetracker.repository;
import com.financetracker.model.Budget;
import com.financetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BudgetRepository extends JpaRepository<Budget,Long> {
    List<Budget> findByUser(User user);
    List<Budget> findByUserAndMonthAndYear(User user,int month,int year);
}
