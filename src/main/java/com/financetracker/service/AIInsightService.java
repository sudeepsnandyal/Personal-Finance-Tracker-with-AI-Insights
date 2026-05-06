package com.financetracker.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.*;
import com.financetracker.model.AIInsight;
import com.financetracker.model.User;
import com.financetracker.repository.AIInsightRepository;
import com.financetracker.repository.ExpenseRepository;
import com.financetracker.repository.IncomeRepository;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class AIInsightService {
    @Autowired private AIInsightRepository insightRepo;
    @Autowired private ExpenseRepository expRepo;
    @Autowired private IncomeRepository incRepo;
    @Autowired private UserService userService;

    @Value("${groq.api.key}") private String apiKey;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String GROQ_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final String MODEL = "llama-3.1-8b-instant";

    public String generateInsight(String userMessage){
        try {
            User user = userService.getCurrentUser();
            double income  = Optional.ofNullable(incRepo.getTotalByUser(user)).orElse(0.0);
            double expense = Optional.ofNullable(expRepo.getTotalByUser(user)).orElse(0.0);
            double balance = income - expense;
            List<Object[]> cats = expRepo.getByCategory(user);
            StringBuilder catStr = new StringBuilder();
            for(Object[] r : cats)
                catStr.append(r[0]).append(": Rs.").append(String.format("%.2f",((Number)r[1]).doubleValue())).append("; ");

            String context = String.format(
                "User: %s. Total Income: Rs.%.2f, Total Expenses: Rs.%.2f, Balance: Rs.%.2f. Expense breakdown: %s",
                user.getName(), income, expense, balance, catStr);

            ObjectNode body = mapper.createObjectNode();
            body.put("model", MODEL);
            body.put("max_tokens", 512);
            ArrayNode messages = mapper.createArrayNode();
            ObjectNode sys = mapper.createObjectNode();
            sys.put("role","system");
            sys.put("content","You are FinTrack AI, a friendly personal finance advisor for Indian users. Use Rs. for currency. Keep responses concise and actionable with bullet points when helpful.");
            messages.add(sys);
            ObjectNode usr = mapper.createObjectNode();
            usr.put("role","user");
            usr.put("content", context + " Question: " + userMessage);
            messages.add(usr);
            body.set("messages", messages);

            OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30,TimeUnit.SECONDS).readTimeout(60,TimeUnit.SECONDS).build();
            Request request = new Request.Builder()
                .url(GROQ_URL)
                .addHeader("Authorization","Bearer "+apiKey)
                .addHeader("Content-Type","application/json")
                .post(RequestBody.create(mapper.writeValueAsString(body), MediaType.parse("application/json")))
                .build();

            try(Response response = client.newCall(request).execute()){
                String respBody = response.body().string();
                if(!response.isSuccessful()) return "AI error "+response.code()+": "+respBody;
                JsonNode json = mapper.readTree(respBody);
                String text = json.path("choices").get(0).path("message").path("content").asText();
                AIInsight insight = new AIInsight();
                insight.setUser(user); insight.setInsightText(text);
                insight.setUserMessage(userMessage); insight.setGeneratedAt(LocalDateTime.now());
                insightRepo.save(insight);
                return text;
            }
        } catch(Exception e){
            e.printStackTrace();
            return "Could not connect to AI. Check your Groq API key in application.properties.";
        }
    }

    public List<AIInsight> getHistory(){
        return insightRepo.findByUserOrderByGeneratedAtDesc(userService.getCurrentUser());
    }
}
