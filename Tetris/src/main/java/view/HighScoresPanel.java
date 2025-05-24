package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HighScoresPanel extends JPanel {
    public HighScoresPanel(ActionListener controller) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(17, 17, 17));
        add(Box.createVerticalStrut(15));

        JLabel label = new JLabel("Рекорды", SwingConstants.CENTER);
        label.setForeground(new Color(100, 150, 255));
        label.setFont(new Font("Courier New", Font.BOLD, 32));

        JPanel scoresPanel = new JPanel();
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));
        scoresPanel.setOpaque(false);

        for (int i = 0; i < 5; i++) {
            JLabel scoreLabel = new JLabel("Игрок " + (i + 1) + " — 1000 очков", SwingConstants.CENTER);
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            scoresPanel.add(scoreLabel);
        }

        JButton button = new StyledButton("Back");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(controller);

        JPanel north = new JPanel(new GridBagLayout());
        north.add(label);
        north.setOpaque(false);
        JPanel center = new JPanel(new GridBagLayout());
        center.add(scoresPanel);
        center.setOpaque(false);
        JPanel south = new JPanel(new GridBagLayout());
        south.add(button);
        south.setOpaque(false);

        add(north);
        add(center);
        add(south);

        add(Box.createVerticalStrut(15));
    }
}