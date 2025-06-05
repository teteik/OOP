package view;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {
    public AboutPanel() {
        setBackground(new Color(17, 17, 17));
        JLabel label = new JLabel("Tetris v1.0\n© 2025", SwingConstants.CENTER);
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(new Font("Courier New", Font.PLAIN, 18));
        add(label);
    }
}
