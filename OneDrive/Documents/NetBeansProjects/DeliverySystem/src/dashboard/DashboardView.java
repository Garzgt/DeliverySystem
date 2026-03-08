package dashboard;

import dashboard.components.Sidebar;
import dashboard.components.TopBar;
import config.Session;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;

    public DashboardView() {
        initComponents();
    }

    private void initComponents() {

        setTitle("Delivery System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== SIDEBAR =====
        Sidebar sidebar = new Sidebar();
        sidebar.setOnMenuClick(this::handleMenuClick);

        // ===== TOPBAR =====
        TopBar topBar = new TopBar();

        // ===== CONTENT AREA =====
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(new Color(240, 242, 245));

        contentPanel.add(createDashboardPage(), "Dashboard");
        contentPanel.add(createSimplePage("Orders"), "Orders");
        contentPanel.add(createSimplePage("Products"), "Products");
        contentPanel.add(createSimplePage("Categories"), "Categories");
        contentPanel.add(createSimplePage("Profile"), "Profile");

        add(sidebar, BorderLayout.WEST);
        add(topBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    // ===== REAL RESPONSIVE DASHBOARD PAGE =====
    private JPanel createDashboardPage() {

        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(new Color(240, 242, 245));
        page.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Dashboard Overview");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        page.add(title, BorderLayout.NORTH);

        // Responsive grid
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        gridPanel.setBackground(new Color(240, 242, 245));

        gridPanel.add(createStatCard("Total Orders", "120"));
        gridPanel.add(createStatCard("Revenue", "$8,450"));
        gridPanel.add(createStatCard("Products", "58"));
        gridPanel.add(createStatCard("Customers", "34"));
        gridPanel.add(createStatCard("Pending Orders", "12"));
        gridPanel.add(createStatCard("Completed Orders", "108"));

        page.add(gridPanel, BorderLayout.CENTER);

        return page;
    }

    private JPanel createStatCard(String title, String value) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(Color.GRAY);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private JPanel createSimplePage(String name) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 242, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel label = new JLabel(name + " Page");
        label.setFont(new Font("Segoe UI", Font.BOLD, 28));

        panel.add(label, BorderLayout.NORTH);

        return panel;
    }

   private void handleMenuClick(String menu) {

    if (menu.equals("Logout")) {
        Session.logout();
        dispose(); // close dashboard
        new login.LoginView().setVisible(true); // reopen login
        return;
    }

    cardLayout.show(contentPanel, menu);
}
}