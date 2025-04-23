package com.packages;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/your_db_name",
                "root", "your_password"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
