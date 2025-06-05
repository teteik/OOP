package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    private String[] strs = {"new_game", "high_scores", "exit"};
    private String[] displayText = {"Новая игра", "Рекорды", "Выход"};

    public MainMenuPanel(ActionListener controller) {
        setLayout(new BorderLayout());
        setBackground(new Color(17, 17, 17)); // Темный фон

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false); // Прозрачность для фона

        JLabel titleLabel = new JLabel("Tetris");
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 48));
        titleLabel.setForeground(new Color(100, 150, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(50));

        for (int i = 0; i < strs.length; i++) {
            StyledButton button = new StyledButton(displayText[i]);
            button.setActionCommand(strs[i]);      // Команда для MenuController
            button.addActionListener(controller);  // Подписываемся на события
            contentPanel.add(button);
            contentPanel.add(Box.createVerticalStrut(15));
        }

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(contentPanel);

        add(centerWrapper, BorderLayout.CENTER);
    }
}