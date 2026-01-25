package com.expense.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
               
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                
                String url = "jdbc:mysql://localhost:3306/expense_tracker";
                String user = "root";
                String password = "Prashant@6204"; 

                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database Connected Successfully!");
                
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}