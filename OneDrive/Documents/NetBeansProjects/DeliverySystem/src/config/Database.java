package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    private static final String HOST = "localhost";
    private static final String PORT = "5432";
    private static final String DB_NAME = "delivery_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";
    
    private static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB_NAME;
    
    private static Connection connection = null;
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Database connected successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
        return connection;
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean testConnection() {
        Connection conn = getConnection();
        return conn != null;
    }
}