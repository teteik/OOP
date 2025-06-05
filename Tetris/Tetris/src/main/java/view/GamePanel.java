package view;

import controller.GameController;
import model.GameModel;
import model.GodTetromino;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Image backgroundImage; // Поле для фона
    private static final int BLOCK_SIZE = 50;
    private static final int NEXT_X_OFFSET = 12;
    private final GameModel model;
    private GameController controller;
    private static final int HEIGHT = 1000;
    private static final int WIDTH = 500;

    public GamePanel(GameModel model) {
        this.model = model;
        this.controller = new GameController(model, this);

        try {
            backgroundImage = new ImageIcon(getClass().getResource("/fit_background.png")).getImage();
        } catch (Exception e) {
            System.out.println("Фон не найден");
            backgroundImage = null;
        }

        setPreferredSize(new Dimension(1920, 1080));
        setBackground(new Color(20, 20, 40)); // Резервный фон
        addKeyListener(controller.getKeyAdapter());
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 1920, 1080, this);
            if (g instanceof Graphics2D g2d) {
                GradientPaint gp = new GradientPaint(0, 0, new Color(30, 30, 60), 0, HEIGHT, new Color(10, 10, 30));
                g2d.setPaint(gp);
                g2d.fillRect(480, 0, 2 * WIDTH - 100, HEIGHT + 80);

                // Отрисовка сетки и игрового поля
                drawColoredField(g2d);
            } else {
                drawDefaultBackground(g);
            }
        } else {
            g.setColor(getBackground());
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }

        // Отрисовка игровых элементов
        drawField(g);
        drawFigure(g);
        drawNextFigure(g);
        drawScore(g);
        drawControls(g);
        drawPauseOverlay(g);
    }

    private void drawDefaultBackground(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private void drawColoredField(Graphics2D g2d) {
        for (int y = 0; y < model.getHEIGHT(); y++) {
            for (int x = 0; x < model.getWIDTH(); x++) {
                g2d.setColor((x + y) % 2 == 0 ? new Color(30, 30, 60) : new Color(40, 40, 80));
                g2d.fillRect(500 + x * BLOCK_SIZE, 50 + y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }

        // Рисуем границы клеток
        g2d.setColor(Color.DARK_GRAY);
        for (int y = 0; y <= model.getHEIGHT(); y++) {
            g2d.drawLine(500, 50 + y * BLOCK_SIZE, 500 + model.getWIDTH() * BLOCK_SIZE, 50 + y * BLOCK_SIZE);
        }
        for (int x = 0; x <= model.getWIDTH(); x++) {
            g2d.drawLine(500 + x * BLOCK_SIZE, 50, 500 + x * BLOCK_SIZE, 50 + model.getHEIGHT() * BLOCK_SIZE);
        }
    }

    private void drawField(Graphics g) {
        int[][] field = model.getField();
        for (int y = 0; y < model.getHEIGHT(); y++) {
            for (int x = 0; x < model.getWIDTH(); x++) {
                if (field[y][x] != 0) {
                    drawBlock(g, x, y, Color.GRAY);
                }
            }
        }
    }

    private void drawFigure(Graphics g) {
        GodTetromino figure = model.getCurrentFigure();
        int[][] block = figure.getBlock();
        Point pos = figure.getPosition();

        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                if (block[y][x] != 0) {
                    drawBlock(g, pos.x + x, pos.y + y, getColor(figure.getForm()));
                }
            }
        }
    }

    private void drawNextFigure(Graphics g) {
        GodTetromino next = model.getNextFigure();
        int[][] block = next.getBlock();
        Point pos = new Point(NEXT_X_OFFSET, 2);

        // Рисуем заголовок
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Следующая:", 500 + NEXT_X_OFFSET * BLOCK_SIZE, 200);

        // Координаты рамки
        int offsetX = 500 + pos.x * BLOCK_SIZE;
        int offsetY = 100 + (pos.y + 1) * BLOCK_SIZE;

        // Рисуем контур рамки
        g.setColor(new Color(255, 255, 255, 80)); // Полупрозрачная белая рамка
        g.drawRect(offsetX - 5, offsetY - 5, 4 * BLOCK_SIZE + 10, 4 * BLOCK_SIZE + 10); // Внешняя рамка
        g.setColor(new Color(255, 255, 255, 30)); // Внутренний бордер
        g.drawRect(offsetX - 4, offsetY - 4, 4 * BLOCK_SIZE + 8, 4 * BLOCK_SIZE + 8);

        // Отрисовываем саму следующую фигуру
        for (int y = 0; y < block.length; y++) {
            for (int x = 0; x < block[0].length; x++) {
                if (block[y][x] != 0) {
                    drawBlock(g, pos.x + x, pos.y + y + 4, getColor(next.getForm()));
                }
            }
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String scoreText = "Счёт: " + model.getScore();
        int textWidth = getFontMetrics(g.getFont()).stringWidth(scoreText);
        int x = 800 + (WIDTH - textWidth) * 13 / 16;
        g.drawString(scoreText, x, 90);
    }

    private void drawBlock(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(500 + x * BLOCK_SIZE, 50 + y * BLOCK_SIZE, BLOCK_SIZE - 1, BLOCK_SIZE - 1);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(500 + x * BLOCK_SIZE, 50 + y * BLOCK_SIZE, BLOCK_SIZE - 1, BLOCK_SIZE - 1);
    }

    private void drawControls(Graphics g) {
        int startX = (WIDTH - WIDTH) * 10 / 16 + 1050;
        int startY = 900;

        // Полупрозрачный фон для подсказок
        g.setColor(new Color(30, 30, 30, 180));
        g.fillRect(startX - 10, startY - 10, 300, 160);

        g.setColor(new Color(200, 200, 255));
        g.setFont(new Font("Noto Sans", Font.PLAIN, 16));

        String[] controls = {
                "Управление:",
                "◀ - сдвинуть влево",
                "▶ - сдвинуть вправо",
                "▼ - ускорить падение",
                "Q - поворот против часовой",
                "E - поворот по часовой",
                "Пробел - пауза",
                "Esc - главное меню"
        };

        for (int i = 0; i < controls.length; i++) {
            g.drawString(controls[i], startX, startY + i * 20);
        }
    }

    private Color getColor(GodTetromino.Form form) {
        return switch (form) {
            case I -> new Color(0, 255, 255);
            case J -> new Color(0, 0, 255);
            case L -> new Color(255, 165, 0);
            case O -> new Color(255, 255, 0);
            case S -> new Color(0, 255, 0);
            case T -> new Color(128, 0, 128);
            case Z -> new Color(255, 0, 0);
        };
    }

    private void drawPauseOverlay(Graphics g) {
        if (model.isPaused()) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Полупрозрачный слой
            g2d.setColor(new Color(0, 0, 0, 150));
            g2d.fillRect(500, 50, WIDTH, HEIGHT);

            // Текст "Пауза"
            g2d.setColor(Color.YELLOW);
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            String text = "Пауза";
            int x = (WIDTH - g2d.getFontMetrics().stringWidth(text)) / 2 + 500;
            int y = HEIGHT / 2 + 50;

            g2d.drawString(text, x, y);
            g2d.dispose();
        }
    }
}
