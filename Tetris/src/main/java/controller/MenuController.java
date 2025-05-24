package controller;

import view.TetrisFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private final TetrisFrame view;

    public MenuController(TetrisFrame view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New Game":
                view.showGame(this);
                break;
            case "High Scores":
                view.showHighScores(this);
                break;
            case "About":
                view.showAbout();
                break;
            case "Back":
                view.showMainMenu(this);
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }
}
