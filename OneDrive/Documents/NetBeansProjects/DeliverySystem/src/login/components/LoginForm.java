package login.components;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JPanel {
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnClear;
    private JLabel lblError;
    
    private Runnable onLoginClick;
    
    public LoginForm() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new GridBagLayout());
        setBackground(new Color(45, 45, 45));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Title
        JLabel lblTitle = new JLabel("LOGIN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitle, gbc);
        
        // Subtitle
        JLabel lblSubtitle = new JLabel("Enter your credentials");
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubtitle.setForeground(Color.GRAY);
        lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        add(lblSubtitle, gbc);
        
        // Username Label
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsername.setForeground(Color.WHITE);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(lblUsername, gbc);
        
        // Username Field
        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 16));
        txtUsername.setPreferredSize(new Dimension(250, 40));
        txtUsername.setBackground(new Color(60, 60, 60));
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setCaretColor(Color.WHITE);
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridy = 3;
        add(txtUsername, gbc);
        
        // Password Label
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        lblPassword.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(lblPassword, gbc);
        
        // Password Field
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPassword.setPreferredSize(new Dimension(250, 40));
        txtPassword.setBackground(new Color(60, 60, 60));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setCaretColor(Color.WHITE);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridy = 5;
        add(txtPassword, gbc);
        
        // Error Label
        lblError = new JLabel(" ");
        lblError.setFont(new Font("Arial", Font.PLAIN, 12));
        lblError.setForeground(new Color(255, 100, 100));
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 6;
        add(lblError, gbc);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(45, 45, 45));
        
        // Login Button
        btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setPreferredSize(new Dimension(120, 40));
        btnLogin.setBackground(new Color(0, 150, 136));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Clear Button
        btnClear = new JButton("CLEAR");
        btnClear.setFont(new Font("Arial", Font.BOLD, 14));
        btnClear.setPreferredSize(new Dimension(120, 40));
        btnClear.setBackground(new Color(100, 100, 100));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        btnClear.setBorderPainted(false);
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnClear);
        
        gbc.gridy = 7;
        add(buttonPanel, gbc);
        
        // Button Actions
        btnLogin.addActionListener(e -> {
            if (onLoginClick != null) {
                onLoginClick.run();
            }
        });
        
        btnClear.addActionListener(e -> clearForm());
        
        // Enter key to login
        txtPassword.addActionListener(e -> {
            if (onLoginClick != null) {
                onLoginClick.run();
            }
        });
    }
    
    public String getUsername() {
        return txtUsername.getText().trim();
    }
    
    public String getPassword() {
        return new String(txtPassword.getPassword());
    }
    
    public void clearForm() {
        txtUsername.setText("");
        txtPassword.setText("");
        lblError.setText(" ");
        txtUsername.requestFocus();
    }
    
    public void showError(String message) {
        lblError.setText(message);
    }
    
    public void clearError() {
        lblError.setText(" ");
    }
    
    public void setOnLoginClick(Runnable callback) {
        this.onLoginClick = callback;
    }
    
    public void setLoading(boolean loading) {
        btnLogin.setEnabled(!loading);
        btnLogin.setText(loading ? "Loading..." : "LOGIN");
    }
}