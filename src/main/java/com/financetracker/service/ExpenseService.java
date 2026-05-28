package com.financetracker.service;
import com.financetracker.model.Expense;
import com.financetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExpenseService {
    @Autowired private ExpenseRepository repo;
    @Autowired private UserService userService;

    public List<Expense> getAll(){ return repo.findByUserOrderByDateDesc(userService.getCurrentUser()); }

    public Expense add(Expense expense){
        expense.setUser(userService.getCurrentUser());
        if(expense.getCategory()==null || expense.getCategory().isBlank())
            expense.setCategory(autoCategory(expense.getDescription()));
        return repo.save(expense);
    }

    public Expense update(Long id, Expense updated){
        Expense ex = repo.findById(id).orElseThrow(()->new RuntimeException("Not found"));
        ex.setCategory(updated.getCategory());
        ex.setAmount(updated.getAmount());
        ex.setDate(updated.getDate());
        ex.setDescription(updated.getDescription());
        return repo.save(ex);
    }

    public void delete(Long id){ repo.deleteById(id); }

    public String autoCategory(String desc){
        if(desc==null) return "Other";
        String d = desc.toLowerCase();
        if(d.contains("swiggy")||d.contains("zomato")||d.contains("food")||d.contains("restaurant")||d.contains("cafe")||d.contains("lunch")||d.contains("dinner")||d.contains("breakfast")) return "Food";
        if(d.contains("uber")||d.contains("ola")||d.contains("petrol")||d.contains("fuel")||d.contains("travel")||d.contains("bus")||d.contains("train")||d.contains("flight")||d.contains("metro")) return "Transport";
        if(d.contains("rent")||d.contains("house")||d.contains("apartment")||d.contains("pg")) return "Rent";
        if(d.contains("netflix")||d.contains("spotify")||d.contains("movie")||d.contains("game")||d.contains("entertainment")||d.contains("amazon prime")) return "Entertainment";
        if(d.contains("electric")||d.contains("water")||d.contains("internet")||d.contains("phone")||d.contains("bill")||d.contains("recharge")) return "Bills";
        if(d.contains("medicine")||d.contains("doctor")||d.contains("hospital")||d.contains("health")||d.contains("pharmacy")) return "Health";
        if(d.contains("book")||d.contains("course")||d.contains("college")||d.contains("school")||d.contains("tuition")) return "Education";
        if(d.contains("amazon")||d.contains("flipkart")||d.contains("shop")||d.contains("mall")||d.contains("myntra")) return "Shopping";
        return "Other";
    }

    public Map<String,Object> getChartData(){
        var user = userService.getCurrentUser();
        List<Object[]> rows = repo.getByCategory(user);
        List<String> labels = new ArrayList<>(); List<Double> values = new ArrayList<>();
        for(Object[] r:rows){ labels.add((String)r[0]); values.add(((Number)r[1]).doubleValue()); }
        double total = Optional.ofNullable(repo.getTotalByUser(user)).orElse(0.0);
        Map<String,Object> m = new HashMap<>();
        m.put("labels",labels); m.put("values",values); m.put("total",total);
        return m;
    }

    public Map<String,Object> getMonthlyTrend(){
        var user = userService.getCurrentUser();
        var start = java.time.LocalDate.now().minusMonths(5).withDayOfMonth(1);
        List<Object[]> rows = repo.getMonthlyTrend(user,start);
        List<String> months = new ArrayList<>(); List<Double> amounts = new ArrayList<>();
        String[] mNames = {"","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        for(Object[] r:rows){
            months.add(mNames[((Number)r[0]).intValue()]+" "+r[1]);
            amounts.add(((Number)r[2]).doubleValue());
        }
        Map<String,Object> m = new HashMap<>();
        m.put("months",months); m.put("amounts",amounts);
        return m;
    }
}
