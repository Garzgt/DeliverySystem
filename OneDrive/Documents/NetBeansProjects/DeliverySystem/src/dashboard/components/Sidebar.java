package dashboard.components;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class Sidebar extends JPanel {

    private Consumer<String> onMenuClick;
    private String activeMenu = "Dashboard";

    public Sidebar() {
        initComponents();
    }

    private void initComponents() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(18, 24, 38));
        setPreferredSize(new Dimension(240, 0));

        add(Box.createVerticalStrut(40));

        JLabel logo = new JLabel("DELIVERY");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(logo);

        add(Box.createVerticalStrut(50));

        add(createMenuButton("Dashboard"));
        add(createMenuButton("Orders"));
        add(createMenuButton("Products"));
        add(createMenuButton("Categories"));
        add(createMenuButton("Profile"));

        add(Box.createVerticalGlue());

        add(createMenuButton("Logout"));
        add(Box.createVerticalStrut(30));
    }

    private JButton createMenuButton(String text) {

        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(220, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(18, 24, 38));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> {
            activeMenu = text;
            refreshMenuStyles();
            if (onMenuClick != null) {
                onMenuClick.accept(text);
            }
        });

        return button;
    }

    private void refreshMenuStyles() {

        for (Component comp : getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                if (btn.getText().equals(activeMenu)) {
                    btn.setBackground(new Color(37, 99, 235));
                } else {
                    btn.setBackground(new Color(18, 24, 38));
                }
            }
        }
    }

    public void setOnMenuClick(Consumer<String> callback) {
        this.onMenuClick = callback;
    }
}