package view;

import controller.GameController;
import model.GameModel;
import model.GodTetromino;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static final int BLOCK_SIZE = 20;
    private final GameModel model;
    private GameController controller;

    public GamePanel(GameModel model) {
        this.model = model;
        this.controller = new GameController(model, this);

        setPreferredSize(new Dimension(model.getWIDTH() * BLOCK_SIZE, model.getHEIGHT() * BLOCK_SIZE));
        setBackground(Color.BLACK);
        addKeyListener(controller.getKeyAdapter());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawField(g);
        drawFigure(g);
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

    private void drawBlock(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE - 1, BLOCK_SIZE - 1);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE - 1, BLOCK_SIZE - 1);
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
}