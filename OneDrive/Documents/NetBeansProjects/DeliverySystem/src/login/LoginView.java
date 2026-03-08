package login;

import login.components.LoginForm;
import login.service.AuthService;
import config.Session;
import dashboard.DashboardView;
import dashboard.components.Toast;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private LoginForm loginForm;
    private AuthService authService;

    public LoginView() {
        authService = new AuthService();
        initComponents();
    }

    private void initComponents() {

        setTitle("Delivery System - Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel background = new JPanel(new GridBagLayout());
        background.setBackground(new Color(25, 25, 25));

        loginForm = new LoginForm();
        loginForm.setOnLoginClick(this::handleLogin);

        background.add(loginForm);

        add(background);
    }

    private void handleLogin() {

        loginForm.clearError();

        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        if (username.isEmpty()) {
            loginForm.showError("Username is required");
            return;
        }

        if (password.isEmpty()) {
            loginForm.showError("Password is required");
            return;
        }

        loginForm.setLoading(true);

        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() {
                return authService.login(username, password);
            }

            @Override
            protected void done() {
                loginForm.setLoading(false);

                try {
                    boolean success = get();

                    if (success) {

                        // ✅ Show modern toast
                        new Toast(LoginView.this,
                                "Welcome, " + Session.getUsername() + "!");

                        // ✅ Small delay before redirect
                        Timer timer = new Timer(300, e -> {
                            dispose(); // close login
                            new DashboardView().setVisible(true); // open dashboard
                        });
                        timer.setRepeats(false);
                        timer.start();

                    } else {
                        loginForm.showError("Invalid username or password");
                    }

                } catch (Exception e) {
                    loginForm.showError("Login failed. Please try again.");
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }
}