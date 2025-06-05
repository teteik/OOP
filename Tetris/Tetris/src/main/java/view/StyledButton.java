package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StyledButton extends JButton {
    private final Color defaultColor;
    private final Color hoverColor;
    private boolean isHovered = false;
    private float currentFontSize = 16f;
    private float pulseDelta = 1f;

    public StyledButton(String text) {
        super(text);
        this.defaultColor = new Color(50, 50, 50);
        this.hoverColor = new Color(70, 70, 70);

        // Настройка кнопки
        setOpaque(true);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Courier New", Font.BOLD, 16));
        setPreferredSize(new Dimension(200, 40));
        setMinimumSize(new Dimension(200, 40));
        setMaximumSize(new Dimension(300, 40));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        // Рисуем закругленные края
        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Обработчики для подсветки
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                repaint();
            }

            public void mouseExited(MouseEvent e) {
                repaint();
            }
        });
        Timer pulseTimer = new Timer(200, e -> {
            if (!isHovered) return;

            currentFontSize += pulseDelta;

            if (currentFontSize > 18 || currentFontSize < 17) {
                pulseDelta *= -1; // меняем направление
            }

            setFont(new Font("Courier New", Font.BOLD, (int) currentFontSize));
        });

        pulseTimer.start();

        // Слежение за наведением
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isRollover()) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(defaultColor);
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g2);
        g2.dispose();
    }
}