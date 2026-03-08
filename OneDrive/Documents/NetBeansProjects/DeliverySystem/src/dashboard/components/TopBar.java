package dashboard.components;

import config.Session;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {

    public TopBar() {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 70));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

        JLabel userLabel = new JLabel(
                Session.getUsername() + "  (" + Session.getRole() + ")"
        );
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

        add(title, BorderLayout.WEST);
        add(userLabel, BorderLayout.EAST);
    }
}