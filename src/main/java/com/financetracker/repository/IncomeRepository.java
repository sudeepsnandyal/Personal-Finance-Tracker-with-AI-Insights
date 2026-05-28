package com.financetracker.repository;
import com.financetracker.model.Income;
import com.financetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface IncomeRepository extends JpaRepository<Income,Long> {
    List<Income> findByUserOrderByDateDesc(User user);
    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.user=:user")
    Double getTotalByUser(@Param("user") User user);
    @Query("SELECT i.source,SUM(i.amount) FROM Income i WHERE i.user=:user GROUP BY i.source")
    List<Object[]> getBySourceForUser(@Param("user") User user);
}
