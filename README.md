# 💰 FinTrack AI — Personal Finance Tracker with AI Insights

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![AI](https://img.shields.io/badge/AI-Groq%20Llama%203.1-purple?style=for-the-badge)
![JWT](https://img.shields.io/badge/Security-JWT-red?style=for-the-badge)

> A full-stack Personal Finance Tracker web application with AI-powered spending insights, built using Java Spring Boot, MySQL, and Groq AI (Llama 3.1).

---

## 📌 Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Database Design](#database-design)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [How to Run](#how-to-run)
- [How to Use](#how-to-use)
- [API Endpoints](#api-endpoints)
- [AI Features](#ai-features)
- [Screenshots](#screenshots)
- [Author](#author)

---

## 📖 About the Project

**FinTrack AI** is a personal finance management web application that helps users track their income, expenses, and budgets with AI-powered financial advice.

The application uses **Groq AI (Llama 3.1)** to analyse user spending patterns and provide personalised financial insights, savings tips, and expense predictions — all for free!

### 🎯 Why This Project?
- Most finance apps are complex and paid
- This app is simple, beautiful and completely free to run
- AI gives personalised advice based on YOUR actual spending data
- Built with industry-standard Java Spring Boot — great for learning

---

## ✨ Features

### 🔐 User Authentication
- Secure Signup and Login
- JWT (JSON Web Token) based authentication
- BCrypt password hashing
- User profile with monthly income tracking
- Each user sees only their own data

### 💚 Income Management
- Add multiple income sources (Salary, Freelance, Passive, Business, Investment)
- View complete income history
- Delete income entries
- Total income calculation

### 💸 Expense Tracking
- Add expenses with category, amount, date and description
- **Auto Categorization** — detects category from description keywords:
  - "Swiggy" / "Zomato" → Food 🍔
  - "Uber" / "Ola" → Transport 🚗
  - "Netflix" / "Spotify" → Entertainment 🎬
  - "Amazon" / "Flipkart" → Shopping 🛍️
  - "Electricity" / "Internet" → Bills ⚡
- Full CRUD operations (Create, Read, Update, Delete)
- Category-wise expense breakdown

### 🎯 Budget Management
- Set monthly budget limits per category
- Real-time budget tracking with progress bars
- **Smart Alerts:**
  - 🟡 Warning when 80%+ of budget used
  - 🔴 Alert when budget exceeded
- Budget status visible on dashboard

### 📊 Dashboard & Charts
- **KPI Cards:** Total Income, Total Expenses, Net Savings, Savings Rate
- **Pie Chart** — Category-wise expense distribution
- **Line Chart** — Monthly spending trend (last 6 months)
- **Bar Chart** — Monthly expense comparison
- Top spending category highlight
- Budget alert notifications

### 🤖 AI Financial Advisor (Groq — Free!)
- Live chat with AI financial advisor
- AI analyses YOUR actual transaction data
- Personalised spending pattern analysis
- Smart saving suggestions
- Budget recommendations
- Investment advice
- 7 Quick Question buttons for common queries
- Chat history saved to database

### 🔮 Expense Prediction
- Predicts next month's expenses
- Based on moving average of last 3 months
- Helps plan budget in advance

---

## 🚀 Tech Stack

### Frontend
| Technology | Purpose |
|------------|---------|
| HTML5 | Page structure |
| CSS3 | Styling and animations |
| JavaScript (Vanilla ES6+) | Frontend logic and API calls |
| Chart.js | Interactive charts |

### Backend
| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming language |
| Spring Boot | 3.2.0 | Main backend framework |
| Spring Web (MVC) | 6.1.1 | REST API creation |
| Spring Data JPA | 3.2.0 | Database operations |
| Spring Security | 6.2.0 | Authentication & Authorization |
| Hibernate ORM | 6.3.1 | Object Relational Mapping |

### Security
| Technology | Purpose |
|------------|---------|
| JWT (JJWT 0.12.3) | Token-based authentication |
| BCrypt | Password hashing |
| Spring Security | Request filtering |

### Database
| Technology | Version | Purpose |
|------------|---------|---------|
| MySQL | 8.0+ | Main relational database |
| Spring Data JPA | 3.2.0 | Repository pattern |
| HikariCP | 5.0.1 | Connection pooling |

### AI Integration
| Technology | Purpose |
|------------|---------|
| Groq API | Free AI API provider |
| Llama 3.1 8B Instant | AI language model |
| OkHttp 4.12.0 | HTTP client for API calls |
| Jackson | JSON parsing |

### Development Tools
| Tool | Purpose |
|------|---------|
| Eclipse IDE | Code editor |
| Maven | Dependency management |
| Spring Boot DevTools | Auto restart |
| MySQL Workbench | Database management |
| Git | Version control |
| GitHub | Code hosting |

---

## 📁 Project Structure

```
finance-tracker-v2/
├── src/
│   └── main/
│       ├── java/com/financetracker/
│       │   ├── FinanceTrackerApplication.java    # Main entry point
│       │   ├── config/
│       │   │   └── SecurityConfig.java           # Spring Security config
│       │   ├── controller/
│       │   │   ├── AuthController.java           # Login/Signup APIs
│       │   │   ├── UserController.java           # User profile APIs
│       │   │   ├── IncomeController.java         # Income CRUD APIs
│       │   │   ├── ExpenseController.java        # Expense CRUD APIs
│       │   │   ├── BudgetController.java         # Budget APIs
│       │   │   ├── DashboardController.java      # Dashboard summary API
│       │   │   └── AIController.java             # AI chat APIs
│       │   ├── dto/
│       │   │   ├── LoginRequest.java             # Login request body
│       │   │   ├── SignupRequest.java            # Signup request body
│       │   │   └── JwtResponse.java              # JWT token response
│       │   ├── model/
│       │   │   ├── User.java                     # User entity
│       │   │   ├── Income.java                   # Income entity
│       │   │   ├── Expense.java                  # Expense entity
│       │   │   ├── Budget.java                   # Budget entity
│       │   │   └── AIInsight.java                # AI chat history entity
│       │   ├── repository/
│       │   │   ├── UserRepository.java           # User DB queries
│       │   │   ├── IncomeRepository.java         # Income DB queries
│       │   │   ├── ExpenseRepository.java        # Expense DB queries
│       │   │   ├── BudgetRepository.java         # Budget DB queries
│       │   │   └── AIInsightRepository.java      # AI history DB queries
│       │   ├── security/
│       │   │   ├── JwtUtil.java                  # JWT token utility
│       │   │   ├── JwtFilter.java                # JWT request filter
│       │   │   └── UserDetailsServiceImpl.java   # User auth service
│       │   └── service/
│       │       ├── AuthService.java              # Auth business logic
│       │       ├── UserService.java              # User business logic
│       │       ├── IncomeService.java            # Income business logic
│       │       ├── ExpenseService.java           # Expense + auto categorize
│       │       ├── BudgetService.java            # Budget + prediction
│       │       ├── DashboardService.java         # Dashboard summary
│       │       └── AIInsightService.java         # Groq AI integration
│       └── resources/
│           ├── application.properties.example    # Config template
│           └── static/
│               └── index.html                    # Complete frontend SPA
├── pom.xml                                       # Maven dependencies
├── .gitignore                                    # Git ignore rules
└── README.md                                     # This file
```

---

## 🗄️ Database Design

### Users Table
```sql
CREATE TABLE users (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,  -- BCrypt hashed
    monthly_income  DECIMAL(15,2) DEFAULT 0,
    created_at      DATETIME
);
```

### Incomes Table
```sql
CREATE TABLE incomes (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    source      VARCHAR(255) NOT NULL,
    amount      DECIMAL(15,2) NOT NULL,
    date        DATE NOT NULL,
    description VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

### Expenses Table
```sql
CREATE TABLE expenses (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    category    VARCHAR(255) NOT NULL,
    amount      DECIMAL(15,2) NOT NULL,
    date        DATE NOT NULL,
    description VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

### Budgets Table
```sql
CREATE TABLE budgets (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT NOT NULL,
    category     VARCHAR(255) NOT NULL,
    limit_amount DECIMAL(15,2) NOT NULL,
    month        INT NOT NULL,
    year         INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

### AI Insights Table
```sql
CREATE TABLE ai_insights (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT NOT NULL,
    user_message TEXT,
    insight_text TEXT,
    generated_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────┐
│              FRONTEND                        │
│   HTML5 + CSS3 + JavaScript + Chart.js      │
│   Single Page Application (SPA)             │
└──────────────────┬──────────────────────────┘
                   │ HTTP Requests + JWT Token
┌──────────────────▼──────────────────────────┐
│           SPRING SECURITY                    │
│   JWT Filter → Validate Token               │
│   BCrypt → Password Hashing                 │
└──────────────────┬──────────────────────────┘
                   │
┌──────────────────▼──────────────────────────┐
│           REST CONTROLLERS                   │
│   Auth / User / Income / Expense            │
│   Budget / Dashboard / AI                   │
└──────────────────┬──────────────────────────┘
                   │
┌──────────────────▼──────────────────────────┐
│           SERVICE LAYER                      │
│   Business Logic + Auto Categorization      │
│   Expense Prediction + Budget Alerts        │
└──────┬───────────────────────┬──────────────┘
       │                       │
┌──────▼───────┐    ┌──────────▼──────────────┐
│  REPOSITORY  │    │      GROQ AI API         │
│  JPA + MySQL │    │   Llama 3.1 8B Model     │
│   Database   │    │   Free AI Insights       │
└──────────────┘    └─────────────────────────┘
```

---

## ✅ Prerequisites

Make sure you have installed:

| Software | Version | Download |
|----------|---------|----------|
| Java JDK | 17+ | https://jdk.java.net/17 |
| Eclipse IDE | Latest | https://eclipse.org/downloads |
| MySQL | 8.0+ | https://dev.mysql.com/downloads |
| Git | Latest | https://git-scm.com/downloads |

---

## ⚙️ Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/sandeepnandyal/finance-tracker-ai.git
```

### 2. Setup MySQL Database
Open MySQL Workbench or CMD and run:
```sql
CREATE DATABASE finance_tracker_v2;
```

### 3. Configure application.properties
Copy the example file:
```
src/main/resources/application.properties.example
→ rename to →
src/main/resources/application.properties
```

Update these values:
```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
groq.api.key=YOUR_GROQ_API_KEY
```

### 4. Get Free Groq API Key
1. Go to https://console.groq.com
2. Sign up with Google
3. Click API Keys → Create API Key
4. Copy key starting with `gsk_...`
5. Paste in application.properties

---

## ▶️ How to Run

### In Eclipse:
1. File → Import → Maven → Existing Maven Projects
2. Browse to cloned folder → Finish
3. Wait for Maven to download dependencies (2-5 mins)
4. Right click `FinanceTrackerApplication.java`
5. Run As → Spring Boot App
6. Wait for: `Started FinanceTrackerApplication`

### Open in Browser:
```
http://localhost:8080
```

---

## 📱 How to Use

### 1. Create Account
- Click Sign Up tab
- Enter name, email, password, monthly income
- Click Create Account

### 2. Add Income
- Click Income in sidebar
- Select source (Salary/Freelance etc.)
- Enter amount and date
- Click Add Income

### 3. Add Expenses
- Click Expenses in sidebar
- Type description (e.g. "Swiggy order" → auto sets Food)
- Enter amount and date
- Click Add Expense

### 4. Set Budget
- Click Budget in sidebar
- Select category and set limit
- System tracks spending vs limit
- Alerts when 80%+ used

### 5. View Dashboard
- See all charts and KPI cards
- View budget alerts
- Check spending trends

### 6. Chat with AI
- Click AI Advisor in sidebar
- Ask any financial question
- AI analyses YOUR data and gives advice
- Use quick buttons for common questions

---

## 🌐 API Endpoints

### Authentication
```
POST /api/auth/signup    → Create new account
POST /api/auth/login     → Login and get JWT token
```

### Income
```
GET    /api/income           → Get all income
POST   /api/income           → Add income
DELETE /api/income/{id}      → Delete income
GET    /api/income/summary   → Get income summary
```

### Expenses
```
GET    /api/expenses         → Get all expenses
POST   /api/expenses         → Add expense
PUT    /api/expenses/{id}    → Update expense
DELETE /api/expenses/{id}    → Delete expense
GET    /api/expenses/chart   → Get chart data
GET    /api/expenses/trend   → Get monthly trend
```

### Budget
```
GET    /api/budgets          → Get all budgets with status
POST   /api/budgets          → Set budget
DELETE /api/budgets/{id}     → Delete budget
GET    /api/budgets/alerts   → Get exceeded budgets
GET    /api/budgets/predict  → Get next month prediction
```

### Dashboard
```
GET    /api/dashboard        → Get complete summary
```

### AI Advisor
```
POST   /api/ai/chat          → Send message to AI
GET    /api/ai/history       → Get chat history
```

---

## 🤖 AI Features

### Auto Categorization Keywords
| Keyword | Category |
|---------|---------|
| Swiggy, Zomato, Food, Restaurant | Food 🍔 |
| Uber, Ola, Petrol, Metro, Bus | Transport 🚗 |
| Rent, House, PG, Apartment | Rent 🏠 |
| Netflix, Spotify, Movie, Game | Entertainment 🎬 |
| Electricity, Internet, Phone, Bill | Bills ⚡ |
| Medicine, Doctor, Hospital | Health 🏥 |
| Book, Course, College, School | Education 📚 |
| Amazon, Flipkart, Mall, Myntra | Shopping 🛍️ |

### AI Advisor Quick Questions
- 📊 Analyse my spending
- 💸 Where am I overspending?
- 💰 Am I saving enough?
- 🎯 Budget recommendation
- ✂️ What can I cut?
- 📈 Investment ideas
- 🏥 Financial health check

---

## 👨‍💻 Author

**Sudeep Nandyal**

- GitHub: [@sandeepnandyal](https://github.com/sandeepnandyal)
- Project Link: [finance-tracker-ai](https://github.com/sandeepnandyal/finance-tracker-ai)

---

## 📄 License

This project is open source and available under the MIT License.

---

⭐ **If you found this project helpful, please give it a star on GitHub!** ⭐
