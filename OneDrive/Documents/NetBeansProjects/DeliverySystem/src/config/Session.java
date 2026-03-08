package config;

import model.User;

public class Session {
    
    private static User currentUser = null;
    
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    
    public static void logout() {
        currentUser = null;
    }
    
    public static String getRole() {
        if (currentUser != null) {
            return currentUser.getRole();
        }
        return null;
    }
    
    public static String getUsername() {
        if (currentUser != null) {
            return currentUser.getUsername();
        }
        return null;
    }
}