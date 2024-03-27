package com.example.schoolapp.config;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class SingeltonConnection {
        @Getter
        private static Connection connection;
        static {
            ResourceBundle bundle = ResourceBundle.getBundle("application");
            String dbUrl = bundle.getString("db.url");
            String dbUsername = bundle.getString("db.username");
            String dbPassword = bundle.getString("db.password");

            // Now you can use these values
            System.out.println("DB URL: " + dbUrl);
            System.out.println("DB Username: " + dbUsername);
            System.out.println("DB Password: " + dbPassword);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection
                        (dbUrl, dbUsername, dbPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}

