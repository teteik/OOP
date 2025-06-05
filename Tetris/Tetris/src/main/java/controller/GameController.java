package controller;

import model.GameModel;
import utils.ScoreManager;
import view.GamePanel;
import view.TetrisFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController implements ActionListener {
    private final GameModel model;
    private final GamePanel panel;
    private Timer timer;

    private boolean isKeyPressed = false;

    public GameController(GameModel model, GamePanel panel) {
        this.model = model;
        this.panel = panel;
        this.timer = new Timer(300, this);
        this.timer.start();
    }

    public KeyAdapter getKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isKeyPressed) return;
                isKeyPressed = true;

                System.out.println("Key pressed: " + KeyEvent.getKeyText(e.getKeyCode()));

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (model.canMove(-1, 0)) model.getCurrentFigure().moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (model.canMove(1, 0)) model.getCurrentFigure().moveRight();
                        break;
                    case KeyEvent.VK_DOWN:
                        if (model.canMove(0, 1)) model.getCurrentFigure().moveDown();
                        break;
                    case KeyEvent.VK_Q:
                        model.getCurrentFigure().rotateLeft();
                        if (!model.canMove(0, 0)) model.getCurrentFigure().rotateRight();
                        break;
                    case KeyEvent.VK_E:
                        model.getCurrentFigure().rotateRight();
                        if (!model.canMove(0, 0)) model.getCurrentFigure().rotateLeft();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        SwingUtilities.invokeLater(() -> {
                            TetrisFrame frame = (TetrisFrame) SwingUtilities.getWindowAncestor(panel);
                            if (frame != null) {
                                frame.showMainMenu(new MenuController(frame));
                            }
                        });
                        break;
                    case KeyEvent.VK_SPACE:
                        model.setPaused(!model.isPaused());
                        if (!model.isPaused()) {
                            timer.start();
                        } else {
                            timer.stop();
                        }
                        panel.repaint(); // Перерисовываем экран
                        break;
                }
                panel.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                isKeyPressed = false;
            }
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!model.canMove(0, 1)) {
            model.placePiece();

            if (model.isGameOver()) {
                timer.stop();

                SwingUtilities.invokeLater(() -> {
                    // Получаем окно
                    TetrisFrame frame = (TetrisFrame) SwingUtilities.getWindowAncestor(panel);

                    // Показываем диалог для ввода имени
                    String name = JOptionPane.showInputDialog(frame, "Введите ваше имя:");

                    if (name == null || name.trim().isEmpty()) {
                        name = "Неизвестный";
                    }

                    // Сохраняем результат
                    ScoreManager.saveScore(name, model.getScore());

                    // Переход в главное меню
                    if (frame != null) {
                        frame.showMainMenu(new MenuController(frame));
                    }
                });

                return;
            }

            model.makeNextFigure();
        } else {
            model.getCurrentFigure().moveDown();
        }

        panel.repaint();
    }
}
