package view;

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
        setPreferredSize(new Dimension(1920, 1080));
        setLocationRelativeTo(null);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        if (device.isFullScreenSupported()) {
            setUndecorated(true);
            setResizable(false);       // Запрещаем изменение размера
            device.setFullScreenWindow(this); // Включаем полноэкранный режим
        } else {
            // Резервный вариант — просто растягиваем окно
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(false);
            System.out.println("Полноэкранный режим не поддерживается — используем MAXIMIZED_BOTH");
        }
    }

    public void init(MenuController menuController) {
        showMainMenu(menuController);
        setVisible(true);
    }

    public void showMainMenu(ActionListener listener) {
        MainMenuPanel panel = new MainMenuPanel(listener);
        switchPanel(panel);
        pack();
    }

    public void showGame(ActionListener listener) {
        GamePanel gamePanel = new GamePanel(new GameModel());
        JPanel centeredPanel = createCenteredPanel(gamePanel);

        switchPanel(centeredPanel);

        SwingUtilities.invokeLater(() -> {
            if (centeredPanel.getComponentCount() > 0 && centeredPanel.getComponent(0) instanceof GamePanel gp) {
                gp.requestFocusInWindow();
            }
        });

        pack();
    }

    public void showHighScores(ActionListener listener) {
        switchPanel(new HighScoresPanel(listener));
        pack();
    }

    public void showAbout() {
        switchPanel(new AboutPanel());
        pack();
    }

    private void switchPanel(JPanel newPanel) {
        if (currentPanel != null) remove(currentPanel);
        currentPanel = newPanel;
        add(newPanel);
        revalidate();
        repaint();

        if (newPanel instanceof GamePanel gp) {
            gp.setFocusable(true);
            gp.requestFocusInWindow();
        }
    }

    private JPanel createCenteredPanel(JPanel panel) {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(Color.DARK_GRAY);
        container.add(panel);
        return container;
    }
}
