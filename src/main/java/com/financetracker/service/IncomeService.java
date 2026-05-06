package com.financetracker.service;
import com.financetracker.model.Income;
import com.financetracker.model.User;
import com.financetracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class IncomeService {
    @Autowired private IncomeRepository repo;
    @Autowired private UserService userService;

    public List<Income> getAll(){ return repo.findByUserOrderByDateDesc(userService.getCurrentUser()); }

    public Income add(Income income){
        income.setUser(userService.getCurrentUser());
        return repo.save(income);
    }

    public Income update(Long id, Income updated){
        Income existing = repo.findById(id).orElseThrow(()->new RuntimeException("Not found"));
        existing.setSource(updated.getSource());
        existing.setAmount(updated.getAmount());
        existing.setDate(updated.getDate());
        existing.setDescription(updated.getDescription());
        return repo.save(existing);
    }

    public void delete(Long id){ repo.deleteById(id); }

    public Map<String,Object> getSummary(){
        User user = userService.getCurrentUser();
        double total = Optional.ofNullable(repo.getTotalByUser(user)).orElse(0.0);
        List<Object[]> sources = repo.getBySourceForUser(user);
        List<String> labels = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        for(Object[] r : sources){ labels.add((String)r[0]); values.add(((Number)r[1]).doubleValue()); }
        Map<String,Object> m = new LinkedHashMap<>();
        m.put("total", total); m.put("labels", labels); m.put("values", values);
        return m;
    }
}
