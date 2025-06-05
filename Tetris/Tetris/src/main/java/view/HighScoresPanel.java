package view;

import utils.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class HighScoresPanel extends JPanel {
    private Image backgroundImage; // Поле для фона

    public HighScoresPanel(ActionListener listener) {
        // Увеличенный размер панели
        setPreferredSize(new Dimension(600, 700));
        setLayout(new BorderLayout());

        // 🔽 Загружаем фоновое изображение из ресурсов
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/background.png")).getImage();
        } catch (Exception e) {
            System.out.println("Фон не найден");
            backgroundImage = null;
        }

        // Таблица рекордов
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(new Color(30, 30, 30));
        textArea.setForeground(Color.CYAN);
        textArea.setFont(new Font("Courier New", Font.BOLD, 18));
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        StyledButton back = new StyledButton("Назад");
        back.setActionCommand("back");
        back.addActionListener(listener);
        back.setPreferredSize(new Dimension(120, 40));

        List<ScoreManager.ScoreEntry> scores = ScoreManager.loadScores();
        StringBuilder sb = new StringBuilder("🏆 ТОП РЕКОРДЫ:\n\n");

        if (scores.isEmpty()) {
            sb.append("Нет записей...");
        } else {
            for (int i = 0; i < Math.min(scores.size(), 10); i++) {
                sb.append(String.format("%2d. %-15s %d%n", i + 1, scores.get(i).name, scores.get(i).score));
            }
        }

        textArea.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(scrollPane);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.setOpaque(false);
        bottomPanel.add(back);

        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Рисуем фон
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        // Добавляем заголовок поверх
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        String title = "ТОП РЕКОРДЫ";
        int titleWidth = getFontMetrics(g.getFont()).stringWidth(title);
        g.drawString(title, (getWidth() - titleWidth) / 2, 40);
    }
}