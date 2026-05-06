package com.financetracker.repository;
import com.financetracker.model.Expense;
import com.financetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findByUserOrderByDateDesc(User user);
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user=:user")
    Double getTotalByUser(@Param("user") User user);
    @Query("SELECT e.category,SUM(e.amount) FROM Expense e WHERE e.user=:user GROUP BY e.category ORDER BY SUM(e.amount) DESC")
    List<Object[]> getByCategory(@Param("user") User user);
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user=:user AND e.category=:cat AND MONTH(e.date)=:m AND YEAR(e.date)=:y")
    Double getSumByCategoryMonthYear(@Param("user") User user,@Param("cat") String cat,@Param("m") int m,@Param("y") int y);
    @Query("SELECT MONTH(e.date),YEAR(e.date),SUM(e.amount) FROM Expense e WHERE e.user=:user AND e.date>=:start GROUP BY YEAR(e.date),MONTH(e.date) ORDER BY YEAR(e.date),MONTH(e.date)")
    List<Object[]> getMonthlyTrend(@Param("user") User user,@Param("start") LocalDate start);
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user=:user AND MONTH(e.date)=:m AND YEAR(e.date)=:y")
    Double getMonthTotal(@Param("user") User user,@Param("m") int m,@Param("y") int y);
}
