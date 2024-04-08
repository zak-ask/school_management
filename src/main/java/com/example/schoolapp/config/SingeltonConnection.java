package com.example.schoolapp.config;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class SingeltonConnection {
        private static Connection connection = null;
        private SingeltonConnection(){
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

        public static Connection getConnection(){
            if (connection == null) {
                // Create a new instance only if it's not initialized yet
                synchronized (SingeltonConnection.class) {
                    if (connection == null) {
                        new SingeltonConnection();
                    }
                }
            }
            return connection;
        }

}

