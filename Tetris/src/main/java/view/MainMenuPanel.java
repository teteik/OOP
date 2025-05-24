package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    private String[] strs = {"New Game", "High Scores", "About", "Exit"};

    public MainMenuPanel(ActionListener controller) {
        setLayout(new BorderLayout());
        setBackground(new Color(17, 17, 17)); // Темный фон

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false); // Чтобы не перекрывать фон

        JLabel titleLabel = new JLabel("Tetris");
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 48));
        titleLabel.setForeground(new Color(100, 150, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(50));

        for (String text : strs) {
            JButton button = new StyledButton(text);
            button.addActionListener(controller);
            contentPanel.add(button);
            contentPanel.add(Box.createVerticalStrut(15));
        }

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(contentPanel);

        add(centerWrapper, BorderLayout.CENTER);
    }
}