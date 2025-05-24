package controller;

import model.GameModel;
import view.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController implements ActionListener {
    private final GameModel model;
    private final GamePanel panel;
    private Timer timer;

    public GameController(GameModel model, GamePanel panel) {
        this.model = model;
        this.panel = panel;
        this.timer = new Timer(500, this);
        this.timer.start();
    }

    public KeyAdapter getKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (model.canMove(-1, 0)) {
                            model.getCurrentFigure().moveLeft();
                            panel.repaint();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (model.canMove(1, 0)) {
                            model.getCurrentFigure().moveRight();
                            panel.repaint();
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (model.canMove(0, 1)) {
                            model.getCurrentFigure().moveDown();
                            panel.repaint();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        model.getCurrentFigure().rotateRight();
                        panel.repaint();
                        break;
                }
            }
        };
    }

    // В классе GameController.java

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.canMove(0, 1)) {
            model.getCurrentFigure().moveDown();
        } else {
            model.placePiece();
            if (model.isGameOver()) {
                timer.stop();
                JOptionPane.showMessageDialog(panel, "Игра окончена!");
                return;
            }
            model.makeFigure(); // создаём новую фигуру
        }
        panel.repaint();
    }
}