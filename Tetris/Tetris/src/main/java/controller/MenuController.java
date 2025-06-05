package controller;

import model.GameModel;
import view.GamePanel;
import view.TetrisFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private final TetrisFrame frame;

    public MenuController(TetrisFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("new_game".equals(command)) {
            //new GameController(new GameModel(), new GamePanel());
            frame.showGame(this);
        } else if ("high_scores".equals(command)) {
            frame.showHighScores(this);
        } else if ("about".equals(command)) {
            frame.showAbout();
        } else if ("back".equals(command)) {
            frame.showMainMenu(new MenuController(frame));
        } else if ("exit".equals(command)) {
            System.exit(0);
        }
    }
}