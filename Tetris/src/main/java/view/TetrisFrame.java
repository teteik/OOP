package view;

import controller.GameController;
import controller.MenuController;
import model.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TetrisFrame extends JFrame {
    private JPanel currentPanel;

    public TetrisFrame() {
        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 600));
        setMinimumSize(new Dimension(300, 400));
        setLocationRelativeTo(null);
    }

    public void init(MenuController menuController) {
        showMainMenu(menuController);
        pack();
        setVisible(true);
    }

    public void showMainMenu(ActionListener listener) {
        switchPanel(new MainMenuPanel(listener));
    }

    public void showGame(ActionListener listener) {
        GameModel model = new GameModel();
        switchPanel(new GamePanel(model));
        pack();
    }

    public void showHighScores(ActionListener listener) {
        switchPanel(new HighScoresPanel(listener));
    }

    public void showAbout() {
        switchPanel(new AboutPanel());
    }

    private void switchPanel(JPanel newPanel) {
        if (currentPanel != null) remove(currentPanel);
        currentPanel = newPanel;
        add(currentPanel);
        revalidate();
        repaint();
    }

    private void repaintUI() {
        currentPanel.repaint();
    }
}