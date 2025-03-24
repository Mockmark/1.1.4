package jm.task.core.jdbc.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static final String USER = "AAAA";
    private static final String PASSWORD = "impish donator versus uncouple bath subside";


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver was not loaded: " + e.getMessage());
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static EntityManagerFactory createEntityManagerFactory() {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.put("hibernate.connection.url", URL);
        properties.put("hibernate.connection.username", USER);
        properties.put("hibernate.connection.password", PASSWORD);
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.highlight_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return Persistence.createEntityManagerFactory("db", properties);
    }
}
