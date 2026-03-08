package dashboard.components;

import javax.swing.*;
import java.awt.*;

public class Toast extends JWindow {

    private float opacity = 0f;

    public Toast(JFrame parent, String message) {

        setBackground(new Color(0,0,0,0));

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(40, 167, 69));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };

        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setOpaque(false);

        JLabel label = new JLabel(message);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panel.add(label, BorderLayout.CENTER);

        add(panel);
        pack();

        // Position top-right
        int x = parent.getX() + parent.getWidth() - getWidth() - 20;
        int y = parent.getY() + 40;
        setLocation(x, y);

        setOpacity(0f);
        setVisible(true);

        animateIn();
    }

    private void animateIn() {

        Timer fadeIn = new Timer(15, null);
        fadeIn.addActionListener(e -> {
            opacity += 0.05f;
            if (opacity >= 1f) {
                opacity = 1f;
                fadeIn.stop();
                autoClose();
            }
            setOpacity(opacity);
        });

        fadeIn.start();
    }

    private void autoClose() {

        Timer delay = new Timer(2000, e -> animateOut());
        delay.setRepeats(false);
        delay.start();
    }

    private void animateOut() {

        Timer fadeOut = new Timer(15, null);
        fadeOut.addActionListener(e -> {
            opacity -= 0.05f;
            if (opacity <= 0f) {
                opacity = 0f;
                fadeOut.stop();
                dispose();
            }
            setOpacity(opacity);
        });

        fadeOut.start();
    }
}