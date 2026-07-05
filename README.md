# 🏦 Banking Management System

A console-based Banking Management System developed using **Core Java, JDBC, and MySQL** following a layered architecture with the DAO design pattern.

## ✨ Features

- Create a new bank account
- Deposit money
- Withdraw money
- Transfer money between accounts
- View transaction history
- View all accounts
- Delete an account
- Store account and transaction data in MySQL

## 🛠️ Technologies Used

- Java
- JDBC
- MySQL
- SQL
- Eclipse IDE
- Git
- GitHub

## ⚙️ Database Configuration

Before running the project, update the database credentials in:

src/util/DBConnection.java

```java
private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

Replace `"your_password"` with your own MySQL password.

Also ensure that the `banking_system` database and the required tables are created before running the application.
