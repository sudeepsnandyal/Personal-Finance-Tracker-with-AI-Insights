package com.financetracker.service;
import com.financetracker.model.Budget;
import com.financetracker.model.User;
import com.financetracker.repository.BudgetRepository;
import com.financetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class BudgetService {
    @Autowired private BudgetRepository repo;
    @Autowired private ExpenseRepository expRepo;
    @Autowired private UserService userService;

    public List<Map<String,Object>> getAllWithStatus(){
        User user = userService.getCurrentUser();
        int m = LocalDate.now().getMonthValue(), y = LocalDate.now().getYear();
        List<Budget> budgets = repo.findByUser(user);
        List<Map<String,Object>> result = new ArrayList<>();
        for(Budget b : budgets){
            double spent = Optional.ofNullable(expRepo.getSumByCategoryMonthYear(user,b.getCategory(),b.getMonth(),b.getYear())).orElse(0.0);
            double limit = b.getLimitAmount().doubleValue();
            double pct = limit>0 ? (spent/limit)*100 : 0;
            Map<String,Object> item = new LinkedHashMap<>();
            item.put("id",b.getId()); item.put("category",b.getCategory());
            item.put("limitAmount",limit); item.put("spent",spent);
            item.put("remaining",limit-spent); item.put("percentage",pct);
            item.put("month",b.getMonth()); item.put("year",b.getYear());
            item.put("alert", pct>=80);
            item.put("exceeded", pct>=100);
            result.add(item);
        }
        return result;
    }

    public Budget add(Budget b){ b.setUser(userService.getCurrentUser()); return repo.save(b); }
    public void delete(Long id){ repo.deleteById(id); }

    public List<Map<String,Object>> getAlerts(){
        List<Map<String,Object>> all = getAllWithStatus();
        List<Map<String,Object>> alerts = new ArrayList<>();
        for(Map<String,Object> b : all)
            if((Boolean)b.get("alert")) alerts.add(b);
        return alerts;
    }

    public Map<String,Object> predictNextMonth(){
        User user = userService.getCurrentUser();
        double total = 0; int count = 0;
        for(int i=1;i<=3;i++){
            LocalDate d = LocalDate.now().minusMonths(i);
            Double v = expRepo.getMonthTotal(user,d.getMonthValue(),d.getYear());
            if(v!=null){ total+=v; count++; }
        }
        double prediction = count>0 ? total/count : 0;
        Map<String,Object> m = new HashMap<>();
        m.put("prediction", Math.round(prediction*100.0)/100.0);
        m.put("basedOnMonths", count);
        return m;
    }
}
