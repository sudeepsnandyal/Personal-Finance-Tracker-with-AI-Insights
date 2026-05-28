package com.financetracker.service;
import com.financetracker.model.User;
import com.financetracker.repository.ExpenseRepository;
import com.financetracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DashboardService {
    @Autowired private IncomeRepository incRepo;
    @Autowired private ExpenseRepository expRepo;
    @Autowired private UserService userService;

    public Map<String,Object> getSummary(){
        User user = userService.getCurrentUser();
        double income  = Optional.ofNullable(incRepo.getTotalByUser(user)).orElse(0.0);
        double expense = Optional.ofNullable(expRepo.getTotalByUser(user)).orElse(0.0);
        double savings = income - expense;
        double savingsRate = income>0 ? (savings/income)*100 : 0;

        List<Object[]> cats = expRepo.getByCategory(user);
        String topCategory = cats.isEmpty() ? "None" : (String)cats.get(0)[0];
        double topAmount   = cats.isEmpty() ? 0 : ((Number)cats.get(0)[1]).doubleValue();
        double topPct      = expense>0 ? (topAmount/expense)*100 : 0;

        Map<String,Object> m = new LinkedHashMap<>();
        m.put("totalIncome", income);
        m.put("totalExpense", expense);
        m.put("savings", savings);
        m.put("savingsRate", Math.round(savingsRate*10.0)/10.0);
        m.put("topCategory", topCategory);
        m.put("topCategoryPct", Math.round(topPct*10.0)/10.0);
        m.put("userName", user.getName());
        m.put("monthlyIncome", user.getMonthlyIncome());
        return m;
    }
}
