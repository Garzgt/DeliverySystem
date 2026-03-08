package login.service;

import config.Database;
import config.Session;
import model.User;
import java.sql.*;

public class AuthService {
    
    public boolean login(String username, String password) {
        
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username is required");
            return false;
        }
        
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password is required");
            return false;
        }
        
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username.trim());
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                
                Session.setCurrentUser(user);
                
                System.out.println("Login successful! Welcome " + user.getUsername());
                return true;
            } else {
                System.out.println("Invalid username or password");
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("Database error during login");
            e.printStackTrace();
            return false;
        }
    }
    
    public void logout() {
        Session.logout();
        System.out.println("Logged out successfully");
    }
}